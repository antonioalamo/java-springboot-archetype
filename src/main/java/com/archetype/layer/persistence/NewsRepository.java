package com.archetype.layer.persistence;

import com.archetype.layer.persistence.document.NewsDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This interface is the point of contact for the Service layer.
@Repository
public interface NewsRepository extends MongoRepository<NewsDocument, String> {

    // We maintain database-specific search methods
    Optional<NewsDocument> findByGuid(String guid);

    boolean existsByGuid(String guid);
}