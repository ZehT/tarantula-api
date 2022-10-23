package com.ambitoscala.tarantula.exception;

public class MoltsDontHappenThatFastException extends RuntimeException {

    private static final long serialVersionUID = 1602095068973906670L;

    private static final String MSG = "molts.dont.happen.that.fast.exception";

    public MoltsDontHappenThatFastException() {
        super(MSG);
    }

}
