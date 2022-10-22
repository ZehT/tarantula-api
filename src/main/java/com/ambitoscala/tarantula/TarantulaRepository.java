package com.ambitoscala.tarantula;

import com.ambitoscala.tarantula.domain.TarantulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarantulaRepository extends JpaRepository<TarantulaEntity, Long> {

    Optional<TarantulaEntity> findTarantulaByName(final String name);

}
