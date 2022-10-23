package com.ambitoscala.commons;

import java.time.format.DateTimeFormatter;

public final class APIConstants {

    public static final DateTimeFormatter DATE_TIME_WITHOUT_SECONDS_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }

}
