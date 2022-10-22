package com.ambitoscala.commons.serializer;

import com.ambitoscala.commons.APIConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    private static final long serialVersionUID = -2876535784334796509L;

    public LocalDateSerializer() {
        super(LocalDate.class);
    }

    public void serialize(final LocalDate value, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
        generator.writeString(value.format(APIConstants.DATE_FORMATTER));
    }

}
