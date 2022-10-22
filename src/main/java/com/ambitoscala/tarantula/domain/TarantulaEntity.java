package com.ambitoscala.tarantula.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@Table(name = "TARANTULA")
@NoArgsConstructor
@AllArgsConstructor
public class TarantulaEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    public static TarantulaEntity of(final TarantulaRequestDTO tarantulaRequest) {
        return TarantulaEntity.builder()
                .name(tarantulaRequest.getName())
                .build();
    }

}
