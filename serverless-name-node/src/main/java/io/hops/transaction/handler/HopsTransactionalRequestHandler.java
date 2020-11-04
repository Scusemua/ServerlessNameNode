package io.hops.transaction.handler;

import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.RecoveryInProgressException;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode.FSNameSystem;
import io.hops.transaction.TransactionInfo;
import io.hops.transaction.lock.HdfsTransactionalLockAcquirer;
import io.hops.transaction.lock.TransactionLockAcquirer;

import java.io.IOException;

public abstract class HopsTransactionalRequestHandler
        extends TransactionalRequestHandler {

    private final String path;

    public HopsTransactionalRequestHandler(HDFSOperationType opType) {
        this(opType, null);
    }

    public HopsTransactionalRequestHandler(HDFSOperationType opType,
                                           String path) {
        super(opType);
        this.path = path;
    }

    @Override
    protected TransactionLockAcquirer newLockAcquirer() {
        return new HdfsTransactionalLockAcquirer();
    }


    @Override
    protected Object execute(final Object namesystem) throws IOException {

        return super.execute(new TransactionInfo() {
            @Override
            public String getContextName(OperationType opType) {
                if (namesystem != null && namesystem instanceof FSNameSystem) {
                    return "NN (" + ((FSNameSystem) namesystem).getNamenodeId() + ") " +
                            opType.toString() + "[" + Thread.currentThread().getId() + "]";
                } else {
                    return opType.toString();
                }
            }

            @Override
            public void performPostTransactionAction() throws IOException {
                if (namesystem != null && namesystem instanceof FSNameSystem) {
                    ((FSNameSystem) namesystem).performPendingSafeModeOperation();
                }
            }
        });
    }

    @Override
    protected final void preTransactionSetup() throws IOException {
        setUp();
    }

    public void setUp() throws IOException {

    }

    @Override
    protected final boolean shouldAbort(Exception e) {
        if (e instanceof RecoveryInProgressException.NonAbortingRecoveryInProgressException) {
            return false;
        }
        return true;
    }
}
