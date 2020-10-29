package com.gmail.benrcarver.serverlessnamenode.exceptions;

import com.gmail.benrcarver.serverlessnamenode.server.namenode.SafeModeInfo;

import java.io.IOException;

/**
 * This exception is thrown when the name node is in safe mode.
 * Client cannot modified namespace until the safe mode is off.
 */
public class SafeModeException  extends IOException {
    private static final long serialVersionUID = 1L;

    public SafeModeException(String text, SafeModeInfo mode) throws IOException {
        super(text + ". Name node is in safe mode.\n" + mode.getTurnOffTip());
    }
}