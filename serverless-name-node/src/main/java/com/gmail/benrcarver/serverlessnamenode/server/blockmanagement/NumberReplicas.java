package com.gmail.benrcarver.serverlessnamenode.server.blockmanagement;

public class NumberReplicas {
    private int liveReplicas;

    // Tracks only the decommissioning replicas
    private int decommissioning;
    // Tracks only the decommissioned replicas
    private int decommissioned;
    private int corruptReplicas;
    private int excessReplicas;
    private int replicasOnStaleNodes;

    NumberReplicas() {
        initialize(0, 0, 0, 0, 0, 0);
    }

    NumberReplicas(int live, int decommissioned, int decommissioning, int corrupt,
                   int excess, int stale) {
        initialize(live, decommissioned, decommissioning, corrupt, excess, stale);
    }

    void initialize(int live, int decommissioned, int decommissioning,
                    int corrupt, int excess, int stale) {
        liveReplicas = live;
        this.decommissioning = decommissioning;
        this.decommissioned = decommissioned;
        corruptReplicas = corrupt;
        excessReplicas = excess;
        replicasOnStaleNodes = stale;
    }

    public int liveReplicas() {
        return liveReplicas;
    }

    /**
     *
     * @return decommissioned replicas + decommissioning replicas
     * It is deprecated by decommissionedAndDecommissioning
     * due to its misleading name.
     */
    @Deprecated
    public int decommissionedReplicas() {
        return decommissionedAndDecommissioning();
    }

    /**
     *
     * @return decommissioned and decommissioning replicas
     */
    public int decommissionedAndDecommissioning() {
        return decommissioned + decommissioning;
    }

    /**
     *
     * @return decommissioned replicas only
     */
    public int decommissioned() {
        return decommissioned;
    }

    /**
     *
     * @return decommissioning replicas only
     */
    public int decommissioning() {
        return decommissioning;
    }

    public int corruptReplicas() {
        return corruptReplicas;
    }

    public int excessReplicas() {
        return excessReplicas;
    }

    /**
     * @return the number of replicas which are on stale nodes.
     * This is not mutually exclusive with the other counts -- ie a
     * replica may count as both "live" and "stale".
     */
    public int replicasOnStaleNodes() {
        return replicasOnStaleNodes;
    }
}
