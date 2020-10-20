package com.gmail.benrcarver.serverlessnamenode.exceptions;

/**
 * Indicates that a method has been passed illegal or invalid argument. This
 * exception is thrown instead of IllegalArgumentException to differentiate the
 * exception thrown in Hadoop implementation from the one thrown in JDK.
 */
public class HadoopIllegalArgumentException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs exception with the specified detail message.
     * @param message detailed message.
     */
    public HadoopIllegalArgumentException(final String message) {
        super(message);
    }
}
