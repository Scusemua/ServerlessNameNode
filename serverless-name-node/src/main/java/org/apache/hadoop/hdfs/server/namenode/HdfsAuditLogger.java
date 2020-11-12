package org.apache.hadoop.hdfs.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.hdfs.security.DelegationTokenSecretManager;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.security.UserGroupInformation;

import java.net.InetAddress;

/**
 * Extension of {@link AuditLogger}.
 */
@InterfaceAudience.Public
@InterfaceStability.Evolving
public abstract class HdfsAuditLogger implements AuditLogger {

    @Override
    public void logAuditEvent(boolean succeeded, String userName,
                              InetAddress addr, String cmd, String src, String dst,
                              FileStatus status) {
        logAuditEvent(succeeded, userName, addr, cmd, src, dst, status, null,
                null);
    }

    /**
     * Same as
     * {@link #logAuditEvent(boolean, String, InetAddress, String, String, String, FileStatus)}
     * with additional parameters related to logging delegation token tracking
     * IDs.
     *
     * @param succeeded Whether authorization succeeded.
     * @param userName Name of the user executing the request.
     * @param addr Remote address of the request.
     * @param cmd The requested command.
     * @param src Path of affected source file.
     * @param dst Path of affected destination file (if any).
     * @param stat File information for operations that change the file's metadata
     *          (permissions, owner, times, etc).
     * @param ugi UserGroupInformation of the current user, or null if not logging
     *          token tracking information
     * @param dtSecretManager The token secret manager, or null if not logging
     *          token tracking information
     */
    public abstract void logAuditEvent(boolean succeeded, String userName,
                                       InetAddress addr, String cmd, String src, String dst,
                                       FileStatus stat, UserGroupInformation ugi,
                                       DelegationTokenSecretManager dtSecretManager);
}
