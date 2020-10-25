package com.gmail.benrcarver.serverlessnamenode.fs.permission;

import java.util.regex.Pattern;

class RawParser extends PermissionParser {
    private static Pattern rawOctalPattern =
            Pattern.compile("^\\s*([01]?)([0-7]{3})\\s*$");
    private static Pattern rawNormalPattern =
            Pattern.compile("\\G\\s*([ugoa]*)([+=-]+)([rwxt]*)([,\\s]*)\\s*");

    private short permission;

    public RawParser(String modeStr) throws IllegalArgumentException {
        super(modeStr, rawNormalPattern, rawOctalPattern);
        permission = (short)combineModes(0, false);
    }

    public short getPermission() {
        return permission;
    }

}