package com.ambitoscala.commons;

import java.time.format.DateTimeFormatter;

public final class APIConstants {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }

}
