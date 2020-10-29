package com.gmail.benrcarver.serverlessnamenode.server.datanode;

import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.LayoutVersion;
import org.apache.hadoop.classification.InterfaceAudience;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

@InterfaceAudience.Private
public class DataNodeLayoutVersion {
    /** Build layout version and corresponding feature matrix */
    public final static Map<Integer, SortedSet<LayoutVersion.LayoutFeature>> FEATURES =
            new HashMap<Integer, SortedSet<LayoutVersion.LayoutFeature>>();

    public static final int CURRENT_LAYOUT_VERSION
            = LayoutVersion.getCurrentLayoutVersion(Feature.values());

    static{
        LayoutVersion.updateMap(FEATURES, LayoutVersion.Feature.values());
        LayoutVersion.updateMap(FEATURES, DataNodeLayoutVersion.Feature.values());
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
        FIRST_LAYOUT(-49, -48, "First datanode layout", false),
        BLOCKID_BASED_LAYOUT(-50,
                "The block ID of a finalized block uniquely determines its position " +
                        "in the directory structure");

        private final LayoutVersion.FeatureInfo info;

        /**
         * DataNodeFeature that is added at layout version {@code lv} - 1.
         * @param lv new layout version with the addition of this feature
         * @param description description of the feature
         */
        Feature(final int lv, final String description) {
            this(lv, lv + 1, description, false);
        }

        /**
         * DataNode feature that is added at layout version {@code ancestoryLV}.
         * @param lv new layout version with the addition of this feature
         * @param ancestorLV layout version from which the new lv is derived from.
         * @param description description of the feature
         * @param reserved true when this is a layout version reserved for previous
         *        version
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

