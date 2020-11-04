package com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode;

import io.hops.exception.StorageException;
import io.hops.exception.TransactionContextException;
import io.hops.metadata.hdfs.entity.RetryCacheEntry;
import io.hops.transaction.EntityManager;
import io.hops.transaction.handler.HDFSOperationType;
import io.hops.transaction.handler.HopsTransactionalRequestHandler;
import io.hops.transaction.lock.LockFactory;
import io.hops.transaction.lock.TransactionLocks;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;

import static io.hops.transaction.lock.LockFactory.getInstance;

public class LightWeightCacheDistributed {

    public static final Log LOG = LogFactory.getLog(LightWeightCacheDistributed.class);
    public static boolean enable = true;

    public LightWeightCacheDistributed() {
    }

    public static RetryCacheEntry getTransactional() throws IOException {
        if (!enable || Server.getCallId() <= 0) {
            return null;
        }

        HopsTransactionalRequestHandler rh = new HopsTransactionalRequestHandler(HDFSOperationType
                .RETRY_CACHE_WAIT_COMPLETION) {
            @Override
            public void acquireLock(TransactionLocks locks) throws IOException {
                LockFactory lf = getInstance();
                locks.add(lf.getRetryCacheEntryLock(Server.getClientId(), Server.getCallId(),
                        Server.getRpcEpoch()));
            }

            @Override
            public Object performTask() throws IOException {
                return LightWeightCacheDistributed.get();
            }
        };
        return (RetryCacheEntry) rh.handle();
    }

    public static RetryCacheEntry putTransactional(final boolean ret) throws IOException {
        if (!enable || Server.getCallId() <= 0) {
            return null;
        }

        HopsTransactionalRequestHandler rh = new HopsTransactionalRequestHandler(HDFSOperationType
                .RETRY_CACHE) {
            @Override
            public void acquireLock(TransactionLocks locks) throws IOException {
                LockFactory lf = getInstance();
                locks.add(lf.getRetryCacheEntryLock(Server.getClientId(), Server.getCallId(),
                        Server.getRpcEpoch()));
            }

            @Override
            public Object performTask() throws IOException {
                RetryCacheEntry entry = new RetryCacheEntry(Server.getClientId(), Server.getCallId(),
                        null, -1, Server.getRpcEpoch(),
                        ret ? RetryCacheEntry.SUCCESS : RetryCacheEntry.FAILED);
                LightWeightCacheDistributed.putEntry(entry);
                return entry;
            }
        };
        return (RetryCacheEntry) rh.handle();
    }

    public static RetryCacheEntry get()
            throws TransactionContextException, StorageException {
        if (!enable || Server.getCallId() <= 0) {
            return null;
        }
        return EntityManager.find(RetryCacheEntry.Finder.ByPK,
                Server.getClientId(), Server.getCallId(), Server.getRpcEpoch());
    }

    public static void put(final byte[] payload, boolean status)
            throws TransactionContextException, StorageException {
        if (!enable || Server.getCallId() <= 0) {
            return;
        }
        RetryCacheEntry entry = new RetryCacheEntry(Server.getClientId(), Server.getCallId(), payload,
                -1, Server.getRpcEpoch(), status ? RetryCacheEntry.SUCCESS : RetryCacheEntry.FAILED);
        putEntry(entry);
    }

    public static void putEntry(RetryCacheEntry entry)
            throws TransactionContextException, StorageException {
        if (enable) {
            EntityManager.update(entry);
        }
    }
}
