package com.gmail.benrcarver.serverlessnamenode.server.namenode;

import java.io.IOException;

import com.gmail.benrcarver.serverlessnamenode.protocol.ClientProtocol;

/**
 * SafeModeInfo contains information related to the safe mode.
 * <p/>
 * An instance of {@link SafeModeInfo} is created when the name node
 * enters safe mode.
 * <p/>
 * During name node startup {@link SafeModeInfo} counts the number of
 * <em>safe blocks</em>, those that have at least the minimal number of
 * replicas, and calculates the ratio of safe blocks to the total number
 * of blocks in the system, which is the size of blocks in
 * {@link FSNameSystem#blockManager}. When the ratio reaches the
 * {@link #threshold} it starts the SafeModeMonitor daemon in order
 * to monitor whether the safe mode {@link #extension} is passed.
 * Then it leaves safe mode and destroys itself.
 * <p/>
 * If safe mode is turned on manually then the number of safe blocks is
 * not tracked because the name node is not intended to leave safe mode
 * automatically in the case.
 *
 * @see ClientProtocol#setSafeMode(HdfsConstants.SafeModeAction, boolean)
 */
public class SafeModeInfo {

    // configuration fields
    /**
     * Safe mode threshold condition %.
     */
    private double threshold;
    /**
     * Safe mode minimum number of datanodes alive
     */
    private int datanodeThreshold;
    /**
     * Safe mode extension after the threshold.
     */
    private int extension;
    /**
     * Min replication required by safe mode.
     */
    private int safeReplication;
    /**
     * threshold for populating needed replication queues
     */
    private double replicationQueueThreshold;

    // internal fields
    /**
     * Time when threshold was reached.
     * <p/>
     * <br> -1 safe mode is off
     * <br> 0 safe mode is on, and threshold is not reached yet
     * <br> >0 safe mode is on, but we are in extension period
     */
    private long reached() throws IOException{
        return HdfsVariables.getSafeModeReached();
    }
    /**
     * time of the last status printout
     */
    private long lastStatusReport = 0;
    /**
     * Was safe mode entered automatically because available resources were low.
     */
    private boolean resourcesLow = false;
    /** counter for tracking startup progress of reported blocks */
    private Counter awaitingReportedBlocksCounter;

    public ThreadLocal<Boolean> safeModePendingOperation =
            new ThreadLocal<>();

    /**
     * Creates SafeModeInfo when the name node enters
     * automatic safe mode at startup.
     *
     * @param conf
     *     configuration
     */
    private SafeModeInfo(Configuration conf) throws IOException {
        this.threshold = conf.getFloat(DFS_NAMENODE_SAFEMODE_THRESHOLD_PCT_KEY,
                DFS_NAMENODE_SAFEMODE_THRESHOLD_PCT_DEFAULT);
        if (threshold > 1.0) {
            LOG.warn("The threshold value should't be greater than 1, threshold: " +
                    threshold);
        }
        this.datanodeThreshold =
                conf.getInt(DFS_NAMENODE_SAFEMODE_MIN_DATANODES_KEY,
                        DFS_NAMENODE_SAFEMODE_MIN_DATANODES_DEFAULT);
        this.extension = conf.getInt(DFS_NAMENODE_SAFEMODE_EXTENSION_KEY, 0);
        this.safeReplication = conf.getInt(DFS_NAMENODE_REPLICATION_MIN_KEY,
                DFS_NAMENODE_REPLICATION_MIN_DEFAULT);
        if (this.safeReplication > 1) {
            LOG.warn("Only safe replication 1 is supported");
            this.safeReplication = 1;
        }

        LOG.info(DFS_NAMENODE_SAFEMODE_THRESHOLD_PCT_KEY + " = " + threshold);
        LOG.info(
                DFS_NAMENODE_SAFEMODE_MIN_DATANODES_KEY + " = " + datanodeThreshold);
        LOG.info(DFS_NAMENODE_SAFEMODE_EXTENSION_KEY + "     = " + extension);

        // default to safe mode threshold (i.e., don't populate queues before leaving safe mode)
        this.replicationQueueThreshold =
                conf.getFloat(DFS_NAMENODE_REPL_QUEUE_THRESHOLD_PCT_KEY,
                        (float) threshold);
        HdfsVariables.setBlockTotal(0);
    }

    /**
     * Creates SafeModeInfo when safe mode is entered manually, or because
     * available resources are low.
     * <p/>
     * The {@link #threshold} is set to 1.5 so that it could never be reached.
     * {@link #blockTotal} is set to -1 to indicate that safe mode is manual.
     *
     * @see SafeModeInfo
     */
    private SafeModeInfo(boolean resourcesLow) throws IOException {
        this.threshold = 1.5f;  // this threshold can never be reached
        this.datanodeThreshold = Integer.MAX_VALUE;
        this.extension = Integer.MAX_VALUE;
        this.safeReplication = Short.MAX_VALUE + 1; // more than maxReplication
        this.replicationQueueThreshold = 1.5f; // can never be reached
        HdfsVariables.setBlockTotal(-1);
        this.resourcesLow = resourcesLow;
        enter();
        reportStatus("STATE* Safe mode is ON.", true);
    }

    private SafeModeInfo(double threshold, int datanodeThreshold, int extension, int safeReplication,
                         double replicationQueueThreshold, boolean resourcesLow) throws IOException {
        this.threshold = threshold;
        this.datanodeThreshold = datanodeThreshold;
        this.extension = extension;
        this.safeReplication = safeReplication;
        this.replicationQueueThreshold = replicationQueueThreshold;
        this.resourcesLow = resourcesLow;
    }

    private SafeModeInfo(){};

    public double getThreshold() {
        return threshold;
    }

    public int getDatanodeThreshold() {
        return datanodeThreshold;
    }

    public int getExtension() {
        return extension;
    }

    public int getSafeReplication() {
        return safeReplication;
    }

    public double getReplicationQueueThreshold() {
        return replicationQueueThreshold;
    }

    public long getLastStatusReport() {
        return lastStatusReport;
    }

    public boolean isResourcesLow() {
        return resourcesLow;
    }

    public long getReached() throws IOException{
        return reached();
    }

    /**
     * Check if safe mode is on.
     *
     * @return true if in safe mode
     */
    private boolean isOn() throws IOException {
        doConsistencyCheck();
        return this.reached() >= 0;
    }

    /**
     * Enter safe mode.
     */
    private void enter() throws IOException {
        if(isLeader()){
            LOG.info("enter safe mode");
            HdfsVariables.setSafeModeReached(0);
        }
    }

    /**
     * Leave safe mode.
     * <p/>
     * Check for invalid, under- & over-replicated blocks in the end of
     * startup.
     */
    private void leave() throws IOException {
        if(!inSafeMode.getAndSet(false)){
            return;
        }
        forceReadTheSafeModeFromDB.set(false);
        // if not done yet, initialize replication queues.
        // In the standby, do not populate replication queues
        if (!isPopulatingReplQueues() && shouldPopulateReplicationQueues()) {
            initializeReplQueues();
        }

        leaveInternal();

        clearSafeBlocks();
    }

    private void leaveInternal() throws IOException {
        long timeInSafeMode = now() - startTime;
        NameNode.stateChangeLog.info(
                "STATE* Leaving safe mode after " + timeInSafeMode / 1000 + " secs");
        NameNode.getNameNodeMetrics().setSafeModeTime((int) timeInSafeMode);

        //Log the following only once (when transitioning from ON -> OFF)
        if (reached() >= 0) {
            NameNode.stateChangeLog.info("STATE* Safe mode is OFF");
        }
        if(isLeader()){
            HdfsVariables.exitSafeMode();
        }
        final NetworkTopology nt =
                blockManager.getDatanodeManager().getNetworkTopology();
        NameNode.stateChangeLog.info(
                "STATE* Network topology has " + nt.getNumOfRacks() + " racks and " +
                        nt.getNumOfLeaves() + " datanodes");
        NameNode.stateChangeLog.info("STATE* UnderReplicatedBlocks has " +
                blockManager.numOfUnderReplicatedBlocks() + " blocks");

        startSecretManagerIfNecessary();
        // If startup has not yet completed, end safemode phase.
        StartupProgress prog = NameNode.getStartupProgress();
        if (prog.getStatus(Phase.SAFEMODE) != Status.COMPLETE) {
            prog.endStep(Phase.SAFEMODE, STEP_AWAITING_REPORTED_BLOCKS);
            prog.endPhase(Phase.SAFEMODE);
        }
    }

    /**
     * Check whether we have reached the threshold for
     * initializing replication queues.
     */
    private boolean canInitializeReplicationQueues() throws IOException {
        return shouldPopulateReplicationQueues() &&
                blockSafe() >= blockReplicationQueueThreshold();
    }

    /**
     * Safe mode can be turned off iff
     * another namenode went out of safe mode or
     * the threshold is reached and
     * the extension time have passed.
     *
     * @return true if can leave or false otherwise.
     */
    private boolean canLeave() throws IOException {
        if(!isLeader()){
            return false;
        }
        if (reached() == 0) {
            return false;
        }
        if (now() - reached() < extension) {
            reportStatus("STATE* Safe mode ON, in safe mode extension.", false);
            return false;
        }
        if (needEnter()) {
            reportStatus("STATE* Safe mode ON, thresholds not met.", false);
            return false;
        }
        return true;
    }

    /**
     * This NameNode tries to help the cluster to get out of safe mode by
     * updating the safe block count.
     * This call will trigger the @link{SafeModeMonitor} if it's not already
     * started.
     * @throws IOException
     */
    private void tryToHelpToGetOut() throws IOException{
        //if the cluster was set in safe mode by the admin we should wait for the admin to get out of safe mode
        //if the cluster has low resources we should wait for the resources to increase to get out of safe mode
        //if the namenode is the leader it should get out by other means.
        if (isManual() || areResourcesLow() || isLeader()) {
            return;
        }
        checkMode();
    }

    /**
     * The cluster already left safe mode, now it's time to for this namenode
     * to leave as well.
     * @throws IOException
     */
    private void clusterLeftSafeModeAlready() throws IOException {
        leaveInternal();
    }

    /**
     * There is no need to enter safe mode
     * if DFS is empty or {@link #threshold} == 0 or another namenode already
     * went out of safe mode
     */
    private boolean needEnter() throws IOException {
        return (threshold != 0 && blockSafe() < blockThreshold()) ||
                (datanodeThreshold != 0 && getNumLiveDataNodes() < datanodeThreshold) ||
                (!nameNodeHasResourcesAvailable());
    }

    /**
     * Check and trigger safe mode if needed.
     */

    private void checkMode() throws IOException {
        if(!isLeader() && !(reached()>=0)){
            return;
        }
        // if smmthread is already running, the block threshold must have been
        // reached before, there is no need to enter the safe mode again
        if (smmthread == null && needEnter()) {
            enter();
            // check if we are ready to initialize replication queues
            if (canInitializeReplicationQueues() && !isPopulatingReplQueues()) {
                initializeReplQueues();
            }
            reportStatus("STATE* Safe mode ON.", false);
            return;
        }
        // the threshold is reached or was reached before
        if (!isOn() ||                           // safe mode is off
                extension <= 0 || threshold <= 0) {  // don't need to wait
            this.leave(); // leave safe mode
            return;
        }
        if (reached() > 0) {  // threshold has already been reached before
            reportStatus("STATE* Safe mode ON.", false);
            return;
        }
        // start monitor
        if(isLeader()){
            HdfsVariables.setSafeModeReached(now());
        }
        startSafeModeMonitor();

        reportStatus("STATE* Safe mode extension entered.", true);

        // check if we are ready to initialize replication queues
        if (canInitializeReplicationQueues() && !isPopulatingReplQueues()) {
            initializeReplQueues();
        }
    }

    private synchronized void startSafeModeMonitor() throws IOException{
        if (smmthread == null) {
            smmthread = new Daemon(new SafeModeMonitor());
            smmthread.start();
            reportStatus("STATE* Safe mode extension entered.", true);
        }
    }

    /**
     * Set total number of blocks.
     */
    private synchronized void setBlockTotal(int total) throws IOException {
        int blockTotal = total;
        int blockThreshold = (int) (blockTotal * threshold);
        int blockReplicationQueueThreshold = (int) (blockTotal * replicationQueueThreshold);
        HdfsVariables.setBlockTotal(blockTotal, blockThreshold, blockReplicationQueueThreshold);
        checkMode();
    }

    private synchronized void updateBlockTotal(int deltaTotal) throws IOException {
        HdfsVariables.updateBlockTotal(deltaTotal, threshold, replicationQueueThreshold);
        setSafeModePendingOperation(true);
    }

    /**
     * Increment number of safe blocks if current block has
     * reached minimal replication.
     *
     * @param blk
     *     current block
     */
    private void incrementSafeBlockCount(short replication, Block blk) throws IOException {
        if (replication == safeReplication) {
            int added = addSafeBlock(blk.getBlockId());

            // Report startup progress only if we haven't completed startup yet.
            //todo this will not work with multiple NN
            StartupProgress prog = NameNode.getStartupProgress();
            if (prog.getStatus(Phase.SAFEMODE) != Status.COMPLETE) {
                if (this.awaitingReportedBlocksCounter == null) {
                    this.awaitingReportedBlocksCounter = prog.getCounter(Phase.SAFEMODE,
                            STEP_AWAITING_REPORTED_BLOCKS);
                }
                this.awaitingReportedBlocksCounter.add(added);
            }

            setSafeModePendingOperation(true);
        }
    }

    /**
     * Decrement number of safe blocks if current block has
     * fallen below minimal replication.
     * @param blk
     *     current block
     * @param replication
     *     current replication
     */
    private void decrementSafeBlockCount(Block blk, short replication)
            throws IOException {
        if (replication == safeReplication - 1) {
            removeSafeBlock(blk.getBlockId());
            setSafeModePendingOperation(true);
        }
    }

    /**
     * Check if safe mode was entered manually or automatically (at startup, or
     * when disk space is low).
     */
    private boolean isManual() {
        return extension == Integer.MAX_VALUE;
    }

    /**
     * Set manual safe mode.
     */
    private synchronized void setManual() {
        extension = Integer.MAX_VALUE;
    }

    /**
     * Check if safe mode was entered due to resources being low.
     */
    private boolean areResourcesLow() {
        return resourcesLow;
    }

    /**
     * Set that resources are low for this instance of safe mode.
     */
    private void setResourcesLow() {
        resourcesLow = true;
    }

    /**
     * A tip on how safe mode is to be turned off: manually or automatically.
     */
    String getTurnOffTip() throws IOException{
        if(!isOn()){
            return "Safe mode is OFF.";
        }

        //Manual OR low-resource safemode. (Admin intervention required)
        String adminMsg = "It was turned on manually. ";
        if (areResourcesLow()) {
            adminMsg = "Resources are low on NN. Please add or free up more "
                    + "resources then turn off safe mode manually. NOTE:  If you turn off"
                    + " safe mode before adding resources, "
                    + "the NN will immediately return to safe mode. ";
        }
        if (isManual() || areResourcesLow()) {
            return adminMsg
                    + "Use \"hdfs dfsadmin -safemode leave\" to turn safe mode off.";
        }

        boolean thresholdsMet = true;
        int numLive = getNumLiveDataNodes();
        String msg = "";

        int blockSafe;
        int blockThreshold;
        int blockTotal;
        try {
            blockSafe = blockSafe();
            blockThreshold = blockThreshold();
            blockTotal = blockTotal();
        } catch (IOException ex) {
            LOG.error(ex);
            return "got exception " + ex.getMessage();
        }
        if (blockSafe < blockThreshold) {
            msg += String.format(
                    "The reported blocks %d needs additional %d"
                            + " blocks to reach the threshold %.4f of total blocks %d.%n",
                    blockSafe, (blockThreshold - blockSafe), threshold, blockTotal);
            thresholdsMet = false;
        } else {
            msg += String.format("The reported blocks %d has reached the threshold" +
                    " %.4f of total blocks %d. ", blockSafe, threshold, blockTotal);
        }
        if (numLive < datanodeThreshold) {
            msg += String.format(
                    "The number of live datanodes %d needs an additional %d live "
                            + "datanodes to reach the minimum number %d.%n",
                    numLive, (datanodeThreshold - numLive), datanodeThreshold);
            thresholdsMet = false;
        } else {
            msg += String.format("The number of live datanodes %d has reached " +
                            "the minimum number %d. ",
                    numLive, datanodeThreshold);
        }
        long reached = reached();
        msg += (reached > 0) ? "In safe mode extension. " : "";
        msg += "Safe mode will be turned off automatically ";

        if (!thresholdsMet) {
            msg += "once the thresholds have been reached.";
        } else if (reached + extension - now() > 0) {
            msg += ("in " + (reached + extension - now()) / 1000 + " seconds.");
        } else {
            msg += "soon.";
        }

        return msg;
    }

    /**
     * Print status every 20 seconds.
     */
    private void reportStatus(String msg, boolean rightNow)  throws IOException{
        long curTime = now();
        if (!rightNow && (curTime - lastStatusReport < 20 * 1000)) {
            return;
        }
        NameNode.stateChangeLog.error(msg + " \n" + getTurnOffTip());
        lastStatusReport = curTime;
    }

    @Override
    public String toString() {
        String blockSafe;
        long blockThreshold=-1;
        long reached = -1;
        try {
            blockSafe = "" + blockSafe();
            blockThreshold = blockThreshold();
            reached = reached();
        } catch (IOException ex) {
            blockSafe = ex.getMessage();
        }
        String resText =
                "Current safe blocks = " + blockSafe + ". Target blocks = " +
                        blockThreshold + " for threshold = %" + threshold +
                        ". Minimal replication = " + safeReplication + ".";
        if (reached > 0) {
            resText += " Threshold was reached " + new Date(reached) + ".";
        }
        return resText;
    }

    /**
     * Checks consistency of the class state.
     * This is costly so only runs if asserts are enabled.
     */
    private void doConsistencyCheck() throws IOException {
        boolean assertsOn = false;
        assert assertsOn = true; // set to true if asserts are on
        if (!assertsOn) {
            return;
        }

        long blockTotal = blockTotal();

        if (blockTotal == -1 /*&& blockSafe == -1*/) {
            return; // manual safe mode
        }
        long blockSafe = blockSafe();
        int activeBlocks = blockManager.getActiveBlockCount();
        if ((blockTotal != activeBlocks) &&
                !(blockSafe >= 0 && blockSafe <= blockTotal)) {
            throw new AssertionError(" SafeMode: Inconsistent filesystem state: " +
                    "SafeMode data: blockTotal=" + blockTotal + " blockSafe=" +
                    blockSafe + "; " + "BlockManager data: active=" + activeBlocks);
        }
    }

    private void adjustBlockTotals(List<Block> deltaSafe, int deltaTotal)
            throws IOException {
        int oldBlockSafe = 0;
        if (LOG.isDebugEnabled()) {
            oldBlockSafe = blockSafe();
        }

        if (deltaSafe != null) {
            for (Block b : deltaSafe) {
                removeSafeBlock(b.getBlockId());
            }
        }

        if (LOG.isDebugEnabled()) {
            int newBlockSafe = blockSafe();
            long blockTotal = blockTotal();
            LOG.debug("Adjusting block totals from " + oldBlockSafe + "/" + blockTotal + " to " + newBlockSafe + "/"
                    + (blockTotal + deltaTotal));
        }

        updateBlockTotal(deltaTotal);
    }

    private void setSafeModePendingOperation(Boolean val) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeModeX Some operation are put on hold");
        }
        safeModePendingOperation.set(val);
    }

    private void adjustSafeBlocks(Set<Long> safeBlocks) throws IOException {
        int added = addSafeBlocks(new ArrayList<Long>(safeBlocks));
        if (LOG.isDebugEnabled()) {
            long blockTotal = blockTotal();
            LOG.debug("Adjusting safe blocks, added " + added +" blocks");
        }

        StartupProgress prog = NameNode.getStartupProgress();
        if (prog.getStatus(Phase.SAFEMODE) != Status.COMPLETE) {
            if (this.awaitingReportedBlocksCounter == null) {
                this.awaitingReportedBlocksCounter = prog.getCounter(Phase.SAFEMODE,
                        STEP_AWAITING_REPORTED_BLOCKS);
            }
            this.awaitingReportedBlocksCounter.add(added);
        }

        setSafeModePendingOperation(true);

        checkMode();
    }

    private void performSafeModePendingOperation() throws IOException {
        if (safeModePendingOperation.get() != null) {
            if (safeModePendingOperation.get()) {
                LOG.debug("SafeMode about to perform pending safe mode operation");
                safeModePendingOperation.set(false);
                checkMode();
            }
        }
    }

    /**
     * Get number of safe blocks from the database
     * @return
     * @throws IOException
     */
    int blockSafe() throws IOException {
        return getBlockSafe();
    }

    /**
     * Get number of total blocks from the database
     * @return
     * @throws IOException
     */
    int blockTotal() throws IOException {
        return HdfsVariables.getBlockTotal();
    }

    /**
     * Get blockReplicationQueueThreshold from the database
     * @return
     * @throws IOException
     */
    int blockReplicationQueueThreshold() throws IOException {
        return HdfsVariables.getBlockReplicationQueueThreshold();
    }

    /**
     * Get blockThreshold from the database
     * @return
     * @throws IOException
     */
    int blockThreshold() throws IOException {
        return HdfsVariables.getBlockThreshold();
    }
}