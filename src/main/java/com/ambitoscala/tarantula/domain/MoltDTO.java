package com.ambitoscala.tarantula.domain;

import com.ambitoscala.commons.serializer.LocalDateTimeDeserializer;
import com.ambitoscala.commons.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoltDTO {

    private String id;

    @ApiModelProperty(value = "Molted At", example = "10/10/2022 10:10")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime moltedAt;

    public static MoltDTO ofDocument(final MoltDocument moltDocument) {
        return MoltDTO.builder()
                //.id(moltDocument.getId().toString())
                .moltedAt(moltDocument.getMoltedAt())
                .build();
    }

}
