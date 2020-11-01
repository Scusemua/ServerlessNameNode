package io.hops.transaction.context;

public enum HdfsTransactionContextMaintenanceCmds
        implements TransactionContextMaintenanceCmds {
    INodePKChanged,
    Concat,   //remove the old inode row and reinsert the row
    BlockDoesNotExist,
    EmptyFile, // a file that doesn't have any blocks
    NoXAttrsAttached;
}
