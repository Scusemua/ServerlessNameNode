package org.apache.hadoop.hdfs.server.protocol;

import org.apache.hadoop.hdfs.protocol.ClientProtocol;
import org.apache.hadoop.hdfs.server.protocol.DatanodeProtocol;
import org.apache.hadoop.hdfs.server.protocol.NamenodeProtocol;
import org.apache.hadoop.ipc.GenericRefreshProtocol;
import org.apache.hadoop.ipc.RefreshCallQueueProtocol;
import org.apache.hadoop.security.RefreshUserMappingsProtocol;
import org.apache.hadoop.security.authorize.RefreshAuthorizationPolicyProtocol;
import org.apache.hadoop.tools.GetUserMappingsProtocol;
import org.apache.hadoop.tracing.TraceAdminProtocol;

/**
 * The full set of RPC methods implemented by the Namenode.
 */
public interface NamenodeProtocols
    extends ClientProtocol,
        DatanodeProtocol,
        NamenodeProtocol,
        RefreshAuthorizationPolicyProtocol,
        RefreshUserMappingsProtocol,
        RefreshCallQueueProtocol,
        GenericRefreshProtocol,
        GetUserMappingsProtocol,
        TraceAdminProtocol {
}
