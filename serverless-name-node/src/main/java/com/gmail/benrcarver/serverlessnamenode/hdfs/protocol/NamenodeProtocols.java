package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import org.apache.hadoop.security.authorize.RefreshAuthorizationPolicyProtocol;

/**
 * The full set of RPC methods implemented by the Namenode.
 */
public interface NamenodeProtocols
    extends ClientProtocol,
            RefreshAuthorizationPolicyProtocol {
}
