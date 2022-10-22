package com.ambitoscala.commons.serializer;

import com.ambitoscala.commons.APIConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import liquibase.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = -7344310195089074513L;

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    public LocalDate deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        LocalDate localDate = null;
        if (StringUtils.isNotEmpty(parser.getValueAsString())) {
            localDate = LocalDate.parse(parser.getValueAsString(), APIConstants.DATE_FORMATTER);
        }
        return localDate;
    }
}
