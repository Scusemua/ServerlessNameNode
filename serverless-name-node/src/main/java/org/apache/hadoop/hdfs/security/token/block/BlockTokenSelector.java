package org.apache.hadoop.hdfs.security.token.block;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.TokenIdentifier;
import org.apache.hadoop.security.token.TokenSelector;

import java.util.Collection;

/**
 * A block token selector for HDFS
 */
@InterfaceAudience.Private
public class BlockTokenSelector implements TokenSelector<BlockTokenIdentifier> {

    @Override
    @SuppressWarnings("unchecked")
    public Token<BlockTokenIdentifier> selectToken(Text service,
                                                   Collection<Token<? extends TokenIdentifier>> tokens) {
        if (service == null) {
            return null;
        }
        for (Token<? extends TokenIdentifier> token : tokens) {
            if (BlockTokenIdentifier.KIND_NAME.equals(token.getKind())) {
                return (Token<BlockTokenIdentifier>) token;
            }
        }
        return null;
    }
}