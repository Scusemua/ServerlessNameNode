package com.gmail.benrcarver.serverlessnamenode;

import java.io.IOException;

/**
 * Indicates that the parent of specified Path is not a directory
 * as expected.
 */
public class ParentNotDirectoryException extends IOException {
    private static final long serialVersionUID = 1L;

    public ParentNotDirectoryException() {
        super();
    }

    public ParentNotDirectoryException(String msg) {
        super(msg);
    }
}