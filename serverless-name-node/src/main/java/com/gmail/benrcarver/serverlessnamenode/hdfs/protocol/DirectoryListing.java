package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import com.gmail.benrcarver.serverlessnamenode.protocol.HdfsFileStatus;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

/**
 * This class defines a partial listing of a directory to support
 * iterative directory listing.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class DirectoryListing {
    private HdfsFileStatus[] partialListing;
    private int remainingEntries;

    /**
     * constructor
     *
     * @param partialListing
     *     a partial listing of a directory
     * @param remainingEntries
     *     number of entries that are left to be listed
     */
    public DirectoryListing(HdfsFileStatus[] partialListing,
                            int remainingEntries) {
        if (partialListing == null) {
            throw new IllegalArgumentException("partial listing should not be null");
        }
        if (partialListing.length == 0 && remainingEntries != 0) {
            throw new IllegalArgumentException("Partial listing is empty but " +
                    "the number of remaining entries is not zero");
        }
        this.partialListing = partialListing;
        this.remainingEntries = remainingEntries;
    }

    /**
     * Get the partial listing of file status
     *
     * @return the partial listing of file status
     */
    public HdfsFileStatus[] getPartialListing() {
        return partialListing;
    }

    /**
     * Get the number of remaining entries that are left to be listed
     *
     * @return the number of remaining entries that are left to be listed
     */
    public int getRemainingEntries() {
        return remainingEntries;
    }

    /**
     * Check if there are more entries that are left to be listed
     *
     * @return true if there are more entries that are left to be listed;
     * return false otherwise.
     */
    public boolean hasMore() {
        return remainingEntries != 0;
    }

    /**
     * Get the last name in this list
     *
     * @return the last name in the list if it is not empty; otherwise return null
     */
    public byte[] getLastName() {
        if (partialListing.length == 0) {
            return null;
        }
        return partialListing[partialListing.length - 1].getLocalNameInBytes();
    }
}
