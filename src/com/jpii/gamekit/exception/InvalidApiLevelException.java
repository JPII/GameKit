package com.jpii.gamekit.exception;

public class InvalidApiLevelException extends Exception {

	private static final long serialVersionUID = 5128551570511484471L;

	/**
	 * Called when an invalid API level is found.
	 * API level of library must be equal to or lower than project
	 * implementing library.
	 * @param message
	 */
	public InvalidApiLevelException(String message) {
        super(message);
    }

	/**
	 * Called when an invalid API level is found.
	 * API level of library must be equal to or lower than project
	 * implementing library.
	 * @param message
	 */
    public InvalidApiLevelException(String message, Throwable throwable) {
        super(message, throwable);
    }

}