package com.gmail.benrcarver.serverlessnamenode.exceptions;

import static com.gmail.benrcarver.serverlessnamenode.util.StringUtils.TraditionalBinaryPrefix.long2String;

public class DSQuotaExceededException extends QuotaExceededException {
    protected static final long serialVersionUID = 1L;

    public DSQuotaExceededException() {
    }

    public DSQuotaExceededException(String msg) {
        super(msg);
    }

    public DSQuotaExceededException(long quota, long count) {
        super(quota, count);
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        if (msg == null) {
            return "The DiskSpace quota" +
                    (pathName == null ? "" : " of " + pathName) +
                    " is exceeded: quota = " + quota + " B = " +
                    long2String(quota, "B", 2) + " but diskspace consumed = " + count +
                    " B = " + long2String(count, "B", 2);
        } else {
            return msg;
        }
    }
}