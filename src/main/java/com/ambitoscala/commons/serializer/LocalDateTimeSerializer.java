package com.ambitoscala.commons.serializer;

import com.ambitoscala.commons.APIConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    private static final long serialVersionUID = -595006385964511670L;

    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(value.format(APIConstants.DATE_TIME_WITHOUT_SECONDS_FORMATTER));
    }

}
