package io.hops.transaction.lock;

import java.io.IOException;

public final class HdfsTransactionalLockAcquirer
        extends TransactionLockAcquirer {

    private final io.hops.transaction.lock.HdfsTransactionLocks locks;

    public HdfsTransactionalLockAcquirer() {
        locks = new io.hops.transaction.lock.HdfsTransactionLocks();
    }

    @Override
    public void acquire() throws IOException {
        for (Lock lock : locks.getSortedLocks()) {
            lock.acquire(locks);
        }
    }

    @Override
    public TransactionLocks getLocks() {
        return locks;
    }
}
