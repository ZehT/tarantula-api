package com.ambitoscala.tarantula.exception;

public class TarantulaAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1030200278603835892L;

    public TarantulaAlreadyExistsException(final String message) {
        super(message);
    }

}
