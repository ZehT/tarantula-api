package com.ambitoscala.tarantula;

import com.ambitoscala.tarantula.domain.TarantulaDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TarantulaRepository extends MongoRepository<TarantulaDocument, ObjectId> {

    Optional<TarantulaDocument> findTarantulaByName(final String name);

    @Query(value = "{name:{'$regex' : '^?0', '$options' : 'i'}}")
    List<TarantulaDocument> findByNameIgnoreCaseRegex(String name);

    Optional<TarantulaDocument> findByIdAndMoltsMoltedAtGreaterThan(final ObjectId objectId, final LocalDateTime sixMonthsAgo);

}
