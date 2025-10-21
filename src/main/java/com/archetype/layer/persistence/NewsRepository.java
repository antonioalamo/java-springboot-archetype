package com.archetype.layer.persistence;

import com.archetype.layer.persistence.entity.NewsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This interface is the point of contact for the Service layer.
@Repository
public interface NewsRepository extends MongoRepository<NewsEntity, String> {

    // We maintain database-specific search methods
    Optional<NewsEntity> findByGuid(String guid);

    boolean existsByGuid(String guid);
}