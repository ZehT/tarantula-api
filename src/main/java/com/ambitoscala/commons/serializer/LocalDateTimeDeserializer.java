package com.ambitoscala.commons.serializer;

import com.ambitoscala.commons.APIConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    private static final long serialVersionUID = -4907966775963734662L;

    protected LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    public LocalDateTime deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        LocalDateTime localDateTime = null;
        if (StringUtils.hasText(parser.getValueAsString())) {
            localDateTime = LocalDateTime.parse(parser.getValueAsString(), APIConstants.DATE_TIME_WITHOUT_SECONDS_FORMATTER);
        }
        return localDateTime;
    }
}
