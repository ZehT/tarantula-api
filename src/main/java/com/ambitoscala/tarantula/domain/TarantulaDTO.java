package com.ambitoscala.tarantula.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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
    private String id;
    private List<MoltDTO> molts;

    public static TarantulaDTO ofTarantulaDocument(final TarantulaDocument tarantulaDocument) {
        final TarantulaDTO tarantulaDTO = TarantulaDTO.builder()
                .id(tarantulaDocument.getId().toString())
                .name(tarantulaDocument.getName())
                .build();
        tarantulaDTO.addMolts(tarantulaDocument.getMolts());
        return tarantulaDTO;
    }

    private void addMolts(final List<MoltDocument> molts) {
        if (!CollectionUtils.isEmpty(molts)) {
            this.molts = molts.stream().map(MoltDTO::ofDocument).collect(Collectors.toList());
        }
    }

}
