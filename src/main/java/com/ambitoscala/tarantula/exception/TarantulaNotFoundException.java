package com.ambitoscala.tarantula.exception;

public class TarantulaNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -585883969528863527L;

    private static final String MSG = "tarantula.not.found.exception";

    public TarantulaNotFoundException() {
        super(MSG);
    }

}
