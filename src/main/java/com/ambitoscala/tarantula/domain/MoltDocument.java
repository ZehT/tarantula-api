package com.ambitoscala.tarantula.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "molts")
public class MoltDocument {

    @Id
    private ObjectId id;

    private LocalDateTime moltedAt;

    public static MoltDocument ofNewMolt() {
        return MoltDocument.builder().moltedAt(LocalDateTime.now()).build();
    }

}
