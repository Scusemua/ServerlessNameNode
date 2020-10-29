package io.hops.transaction.lock;

import io.hops.transaction.lock.Lock;
import io.hops.transaction.lock.TransactionLockAcquirer;
import io.hops.transaction.lock.TransactionLocks;

import java.io.IOException;

public final class HdfsTransactionalLockAcquirer
        extends TransactionLockAcquirer {

    private final com.gmail.benrcarver.serverlessnamenode.hops.transaction.lock.HdfsTransactionLocks locks;

    public HdfsTransactionalLockAcquirer() {
        locks = new com.gmail.benrcarver.serverlessnamenode.hops.transaction.lock.HdfsTransactionLocks();
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
