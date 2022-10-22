package com.ambitoscala.tarantula.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Class that represents a Tarantula.</p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TarantulaDTO extends TarantulaRequestDTO {

    @ApiModelProperty(value = "Tarantula ID", example = "1")
    private Long id;

    public static TarantulaDTO ofEntity(final TarantulaEntity tarantulaEntity) {
        return TarantulaDTO.builder()
                .id(tarantulaEntity.getId())
                .build();
    }

}
