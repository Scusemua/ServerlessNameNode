package io.hops.transaction.lock;

import io.hops.transaction.lock.Lock;
import io.hops.transaction.lock.TransactionLocks;

import java.util.*;

public class HdfsTransactionLocks implements TransactionLocks {

    private final Map<Lock.Type, Lock> locks;

    public HdfsTransactionLocks() {
        this.locks = new EnumMap<>(Lock.Type.class);
    }

    @Override
    public TransactionLocks add(Lock lock) {
        if (locks.containsKey(lock.getType())) {
            throw new IllegalArgumentException(
                    "The same lock cannot be added " + "twice!");
        }

        locks.put(lock.getType(), lock);
        return this;
    }

    @Override
    public TransactionLocks add(Collection<Lock> locks) {
        for (Lock lock : locks) {
            add(lock);
        }
        return this;
    }

    @Override
    public boolean containsLock(Lock.Type lock) {
        return locks.containsKey(lock);
    }

    @Override
    public Lock getLock(Lock.Type type) throws TransactionLocks.LockNotAddedException {
        if (!locks.containsKey(type)) {
            throw new LockNotAddedException(
                    "Trying to get a lock which was not " + "added.");
        }
        return locks.get(type);
    }

    public List<Lock> getSortedLocks() {
        List<Lock> lks = new ArrayList<>(locks.values());
        Collections.sort(lks);
        return lks;
    }


}
