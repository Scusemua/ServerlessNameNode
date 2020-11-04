package com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.protocol;

import java.util.Arrays;

/**
 * Contains a list of paths corresponding to corrupt files and a cookie
 * used for iterative calls to NameNode.listCorruptFileBlocks.
 */
public class CorruptFileBlocks {
    // used for hashCode
    private static final int PRIME = 16777619;

    private String[] files;
    private String cookie;

    public CorruptFileBlocks() {
        this(new String[0], "");
    }

    public CorruptFileBlocks(String[] files, String cookie) {
        this.files = files;
        this.cookie = cookie;
    }

    public String[] getFiles() {
        return files;
    }

    public String getCookie() {
        return cookie;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CorruptFileBlocks)) {
            return false;
        }
        CorruptFileBlocks other = (CorruptFileBlocks) obj;
        return cookie.equals(other.cookie) && Arrays.equals(files, other.files);
    }


    @Override
    public int hashCode() {
        int result = cookie.hashCode();

        for (String file : files) {
            result = PRIME * result + file.hashCode();
        }

        return result;
    }
}
