package com.ambitoscala.tarantula.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>Class that represents a Tarantula to be created.</p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TarantulaRequestDTO {

    @ApiModelProperty(value = "Tarantula name", example = "Lorem Ipsum", required = true, position = 1)
    @NotBlank(message = "commons.validation.mandatory.field")
    @Size(max = 50, message = "commons.validation.maxsize.fifty")
    private String name;

}
