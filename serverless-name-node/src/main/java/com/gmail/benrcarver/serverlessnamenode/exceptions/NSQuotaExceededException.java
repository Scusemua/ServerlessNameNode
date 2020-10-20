package com.gmail.benrcarver.serverlessnamenode.exceptions;

public class NSQuotaExceededException extends QuotaExceededException {
    protected static final long serialVersionUID = 1L;

    private String prefix;

    public NSQuotaExceededException() {}

    public NSQuotaExceededException(String msg) {
        super(msg);
    }

    public NSQuotaExceededException(long quota, long count) {
        super(quota, count);
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        if (msg == null) {
            msg = "The NameSpace quota (directories and files)" +
                    (pathName==null?"":(" of directory " + pathName)) +
                    " is exceeded: quota=" + quota + " file count=" + count;

            if (prefix != null) {
                msg = prefix + ": " + msg;
            }
        }
        return msg;
    }

    /** Set a prefix for the error message. */
    public void setMessagePrefix(final String prefix) {
        this.prefix = prefix;
    }
}