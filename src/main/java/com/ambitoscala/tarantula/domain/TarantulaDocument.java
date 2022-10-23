package com.ambitoscala.tarantula.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tarantulas")
public class TarantulaDocument {

    @Id
    private ObjectId id;

    private String name;
    private List<MoltDocument> molts;

    public static TarantulaDocument of(final TarantulaRequestDTO tarantulaRequest) {
        return TarantulaDocument.builder()
                .name(tarantulaRequest.getName())
                .build();
    }

    public void addMolt(final MoltDocument moltDocument) {
        if (CollectionUtils.isEmpty(this.molts)) {
            this.molts = new ArrayList<>();
        }
        this.molts.add(moltDocument);
    }

}
