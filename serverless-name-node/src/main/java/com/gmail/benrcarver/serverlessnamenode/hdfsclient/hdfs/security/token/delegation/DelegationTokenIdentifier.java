package com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.security.token.delegation;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenIdentifier;
import org.apache.hadoop.security.token.delegation.web.DelegationTokenAuthenticationFilter;
import org.apache.hadoop.security.token.delegation.web.DelegationTokenManager;
import org.apache.hadoop.security.token.delegation.web.KerberosDelegationTokenAuthenticationHandler;

/**
 * Concrete delegation token identifier used by {@link DelegationTokenManager},
 * {@link KerberosDelegationTokenAuthenticationHandler} and
 * {@link DelegationTokenAuthenticationFilter}.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class DelegationTokenIdentifier
        extends AbstractDelegationTokenIdentifier {

    private Text kind;

    public DelegationTokenIdentifier(Text kind) {
        this.kind = kind;
    }

    /**
     * Create a new delegation token identifier
     *
     * @param kind token kind
     * @param owner the effective username of the token owner
     * @param renewer the username of the renewer
     * @param realUser the real username of the token owner
     */
    public DelegationTokenIdentifier(Text kind, Text owner, Text renewer,
                                     Text realUser) {
        super(owner, renewer, realUser);
        this.kind = kind;
    }

    /**
     * Return the delegation token kind
     * @return returns the delegation token kind
     */
    @Override
    public Text getKind() {
        return kind;
    }

}
