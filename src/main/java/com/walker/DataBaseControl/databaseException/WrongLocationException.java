package com.walker.DataBaseControl.databaseException;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class WrongLocationException extends Exception{
    /**
     * Default class constructor
     */
    public WrongLocationException() {
    }

    /**
     * Class constructor
     * @param message Describe problem, that occurs at runtime
     */
    public WrongLocationException(String message) {
        super(message);
    }

    /**
     * Class constructor
     * @param message Describe problem, that occurs
     * @param cause Indicate the cause
     */
    public WrongLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor
     * @param cause Indicate the cause
     */
    public WrongLocationException(Throwable cause) {
        super(cause);
    }
}