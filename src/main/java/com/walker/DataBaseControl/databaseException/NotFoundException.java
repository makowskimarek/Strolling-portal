package com.walker.DataBaseControl.databaseException;

/**
 * Created by Rafal on 09.06.2017.
 */
public class NotFoundException extends Exception{

    /**
     * Default class constructor
     */
    public NotFoundException() {
    }

    /**
     * Class constructor
     * @param message Describe problem, that occurs at runtime
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Class constructor
     * @param message Describe problem, that occurs
     * @param cause Indicate the cause
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor
     * @param cause Indicate the cause
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }

}
