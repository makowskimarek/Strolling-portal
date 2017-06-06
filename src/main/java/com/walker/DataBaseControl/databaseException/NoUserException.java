package com.walker.DataBaseControl.databaseException;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class NoUserException extends Exception{
    /**
     * Default class constructor
     */
    public NoUserException() {
    }

    /**
     * Class constructor
     * @param message Describe problem, that occurs at runtime
     */
    public NoUserException(String message) {
        super(message);
    }

    /**
     * Class constructor
     * @param message Describe problem, that occurs
     * @param cause Indicate the cause
     */
    public NoUserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor
     * @param cause Indicate the cause
     */
    public NoUserException(Throwable cause) {
        super(cause);
    }
}