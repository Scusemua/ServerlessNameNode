package com.gmail.benrcarver.Fission.Exceptions;

public class ExitException extends Throwable {
    /** Status code */
    private int status;

    private String msg;

    /**
     * Constructs an exit exception.
     * @param status the status code returned via System.exit()
     */
    public ExitException(int status) {
        this.status = status;
    }

    /**
     * Constructs an exit exception.
     * @param msg the message to be displayed.
     * @param status the status code returned via System.exit()
     */
    public ExitException(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMessage() {
        return msg;
    }

    /**
     * The status code returned by System.exit()
     *
     * @return the status code returned by System.exit()
     */
    public int getStatus() {
        return status;
    }
}
