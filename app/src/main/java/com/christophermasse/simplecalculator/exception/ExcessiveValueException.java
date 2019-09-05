package com.christophermasse.simplecalculator.exception;

/**
 * Example of a custom exception; to be thrown whenever an output is too big
 */

public class ExcessiveValueException extends RuntimeException {

    public ExcessiveValueException(String message) {
        super(message);
    }
}
