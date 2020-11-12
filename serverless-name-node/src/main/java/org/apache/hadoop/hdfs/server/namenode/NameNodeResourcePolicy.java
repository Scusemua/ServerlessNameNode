package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.classification.InterfaceAudience;

import java.util.Collection;

/**
 * Given a set of checkable resources, this class is capable of determining
 * whether sufficient resources are available for the NN to continue operating.
 */
@InterfaceAudience.Private
final class NameNodeResourcePolicy {

    /**
     * Return true if and only if there are sufficient NN
     * resources to continue logging edits.
     *
     * @param resources
     *     the collection of resources to check.
     * @param minimumRedundantResources
     *     the minimum number of redundant resources
     *     required to continue operation.
     * @return true if and only if there are sufficient NN resources to
     * continue logging edits.
     */
    static boolean areResourcesAvailable(
            Collection<? extends CheckableNameNodeResource> resources,
            int minimumRedundantResources) {

        // TODO: workaround:
        // - during startup, if there are no edits dirs on disk, then there is
        // a call to areResourcesAvailable() with no dirs at all, which was
        // previously causing the NN to enter safemode
        if (resources.isEmpty()) {
            return true;
        }

        int requiredResourceCount = 0;
        int redundantResourceCount = 0;
        int disabledRedundantResourceCount = 0;
        for (CheckableNameNodeResource resource : resources) {
            if (!resource.isRequired()) {
                redundantResourceCount++;
                if (!resource.isResourceAvailable()) {
                    disabledRedundantResourceCount++;
                }
            } else {
                requiredResourceCount++;
                if (!resource.isResourceAvailable()) {
                    // Short circuit - a required resource is not available.
                    return false;
                }
            }
        }

        if (redundantResourceCount == 0) {
            // If there are no redundant resources, return true if there are any
            // required resources available.
            return requiredResourceCount > 0;
        } else {
            return redundantResourceCount - disabledRedundantResourceCount >=
                    minimumRedundantResources;
        }
    }
}
