package com.archetype.news.persistence.internal;

import com.archetype.news.domain.model.Type;
import com.archetype.news.persistence.document.SpeciesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends MongoRepository<SpeciesDocument, Integer> {

    Optional<SpeciesDocument> findByName(String name);

    List<SpeciesDocument> findAllByFirstType(Type firstType);

    List<SpeciesDocument> findAllByFirstTypeAndSecondType(Type firstType, Type lastType);

    List<SpeciesDocument> findAllByFirstTypeOrSecondType(Type firstType, Type secondType);

    SpeciesDocument getByNationalIdIs(int nationalId);
}
