package com.ambitoscala.tarantula.exception;

public class NoContentException extends RuntimeException {

    private static final long serialVersionUID = -7628416526428032800L;
    private static final String MSG = "no.content.exception";

    public NoContentException() {
        super(MSG);
    }

}
