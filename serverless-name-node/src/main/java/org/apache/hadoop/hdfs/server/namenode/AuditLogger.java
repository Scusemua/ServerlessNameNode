package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;

import java.net.InetAddress;

/**
 * Interface defining an audit logger.
 */
public interface AuditLogger {

    /**
     * Called during initialization of the logger.
     *
     * @param conf
     *     The configuration object.
     */
    void initialize(Configuration conf);

    /**
     * Called to log an audit event.
     * <p/>
     * This method must return as quickly as possible, since it's called
     * in a critical section of the NameNode's operation.
     *
     * @param succeeded
     *     Whether authorization succeeded.
     * @param userName
     *     Name of the user executing the request.
     * @param addr
     *     Remote address of the request.
     * @param cmd
     *     The requested command.
     * @param src
     *     Path of affected source file.
     * @param dst
     *     Path of affected destination file (if any).
     * @param stat
     *     File information for operations that change the file's
     *     metadata (permissions, owner, times, etc).
     */
    void logAuditEvent(boolean succeeded, String userName, InetAddress addr,
                       String cmd, String src, String dst, FileStatus stat);

}
