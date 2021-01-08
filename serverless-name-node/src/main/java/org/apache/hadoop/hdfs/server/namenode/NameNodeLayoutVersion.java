package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.hdfs.protocol.LayoutVersion;
import org.apache.hadoop.classification.InterfaceAudience;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

@InterfaceAudience.Private
public class NameNodeLayoutVersion {
    /** Build layout version and corresponding feature matrix */
    public final static Map<Integer, SortedSet<LayoutVersion.LayoutFeature>> FEATURES
            = new HashMap<Integer, SortedSet<LayoutVersion.LayoutFeature>>();

    public static final int CURRENT_LAYOUT_VERSION
            = LayoutVersion.getCurrentLayoutVersion(Feature.values());

    static {
        LayoutVersion.updateMap(FEATURES, LayoutVersion.Feature.values());
        LayoutVersion.updateMap(FEATURES, NameNodeLayoutVersion.Feature.values());
    }

    public static SortedSet<LayoutVersion.LayoutFeature> getFeatures(int lv) {
        return FEATURES.get(lv);
    }

    public static boolean supports(final LayoutVersion.LayoutFeature f, final int lv) {
        return LayoutVersion.supports(FEATURES, f, lv);
    }

    /**
     * Enums for features that change the layout version.
     * <br><br>
     * To add a new layout version:
     * <ul>
     * <li>Define a new enum constant with a short enum name, the new layout version
     * and description of the added feature.</li>
     * <li>When adding a layout version with an ancestor that is not same as
     * its immediate predecessor, use the constructor where a specific ancestor
     * can be passed.
     * </li>
     * </ul>
     */
    public static enum Feature implements LayoutVersion.LayoutFeature {
        ROLLING_UPGRADE(-49, -48, "Support rolling upgrade", false),
        BLOCK_STORAGE_POLICY(-50, "Block Storage policy"),
        TRUNCATE(-51, "Truncate"),
        APPEND_NEW_BLOCK(-52, "Support appending to new block"),
        QUOTA_BY_STORAGE_TYPE(-53, "Support quota for specific storage types"),
        XATTRS(-54, "Extended attributes"),
        XATTRS_NAMESPACE_EXT(-55, "Increase number of xattr namespaces");

        private final LayoutVersion.FeatureInfo info;

        /**
         * Feature that is added at layout version {@code lv} - 1.
         * @param lv new layout version with the addition of this feature
         * @param description description of the feature
         */
        Feature(final int lv, final String description) {
            this(lv, lv + 1, description, false);
        }

        /**
         * NameNode feature that is added at layout version {@code ancestoryLV}.
         * @param lv new layout version with the addition of this feature
         * @param ancestorLV layout version from which the new lv is derived from.
         * @param description description of the feature
         * @param reserved true when this is a layout version reserved for previous
         *        versions
         * @param features set of features that are to be enabled for this version
         */
        Feature(final int lv, final int ancestorLV, final String description,
                boolean reserved, Feature... features) {
            info = new LayoutVersion.FeatureInfo(lv, ancestorLV, description, reserved, features);
        }

        @Override
        public LayoutVersion.FeatureInfo getInfo() {
            return info;
        }
    }
}

