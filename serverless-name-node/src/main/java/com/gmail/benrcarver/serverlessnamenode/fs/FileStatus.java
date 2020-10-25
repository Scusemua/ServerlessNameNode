package com.gmail.benrcarver.serverlessnamenode.fs;

import com.gmail.benrcarver.serverlessnamenode.fs.permission.FsPermission;
import com.gmail.benrcarver.serverlessnamenode.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class FileStatus implements Writable, Comparable<Object>,
        Serializable, ObjectInputValidation {

    private static final long serialVersionUID = 0x13caeae8;

    private Path path;
    private long length;
    private Boolean isdir;
    private short block_replication;
    private long blocksize;
    private long modification_time;
    private long access_time;
    private FsPermission permission;
    private String owner;
    private String group;
    private Path symlink;
    private Set<AttrFlags> attr;

    @Override
    public void validateObject() throws InvalidObjectException {

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {

    }
}
