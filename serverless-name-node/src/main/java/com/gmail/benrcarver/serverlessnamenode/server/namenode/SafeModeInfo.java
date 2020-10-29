package com.gmail.benrcarver.serverlessnamenode.server.namenode;

import java.io.IOException;

/**
 * SafeModeInfo contains information related to the safe mode.
 */
public class SafeModeInfo {

    public String getTurnOffTip() throws IOException {
        return "SAFE MODE NOT SUPPORTED RIGHT NOW.";
    }
}
