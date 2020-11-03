package com.gmail.benrcarver.serverlessnamenode.hdfs.web;

import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.HdfsFileStatus;

public class WebHdfsConstants {
    public static final String WEBHDFS_SCHEME = "webhdfs";
    public static final String SWEBHDFS_SCHEME = "swebhdfs";
    public static final Text WEBHDFS_TOKEN_KIND = new Text("WEBHDFS delegation");
    public static final Text SWEBHDFS_TOKEN_KIND = new Text("SWEBHDFS delegation");

    enum PathType {
        FILE, DIRECTORY, SYMLINK;

        static PathType valueOf(HdfsFileStatus status) {
            return status.isDir()? DIRECTORY: status.isSymlink()? SYMLINK: FILE;
        }
    }
}