package org.foodkart.exceptions;

public class InactiveSessionException extends RuntimeException {
    public InactiveSessionException(String s) {
        super(s);
    }
}
