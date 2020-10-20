package com.gmail.benrcarver.serverlessnamenode.exceptions;

import java.io.IOException;

/**
 * Thrown when a symbolic link is encountered in a path.
 */
public class UnresolvedLinkException extends IOException {
    private static final long serialVersionUID = 1L;

    public UnresolvedLinkException() {
        super();
    }

    public UnresolvedLinkException(String msg) {
        super(msg);
    }
}
