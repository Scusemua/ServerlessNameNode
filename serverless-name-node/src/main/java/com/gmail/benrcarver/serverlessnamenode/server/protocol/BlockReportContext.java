package com.gmail.benrcarver.serverlessnamenode.server.protocol;

import org.apache.hadoop.classification.InterfaceAudience;

/**
 * The context of the block report.
 *
 * This is a set of fields that the Datanode sends to provide context about a
 * block report RPC.  The context includes a unique 64-bit ID which
 * identifies the block report as a whole.  It also includes the total number
 * of RPCs which this block report is split into, and the index into that
 * total for the current RPC.
 */
@InterfaceAudience.Private
public class BlockReportContext {
    private final int totalRpcs;
    private final int curRpc;
    private final long reportId;

    public BlockReportContext(int totalRpcs, int curRpc, long reportId) {
        this.totalRpcs = totalRpcs;
        this.curRpc = curRpc;
        this.reportId = reportId;
    }

    public int getTotalRpcs() {
        return totalRpcs;
    }

    public int getCurRpc() {
        return curRpc;
    }

    public long getReportId() {
        return reportId;
    }
}
