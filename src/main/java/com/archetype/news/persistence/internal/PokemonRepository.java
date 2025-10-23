package com.archetype.news.persistence.internal;

import com.archetype.news.persistence.document.PokemonDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PokemonRepository extends MongoRepository<PokemonDocument, UUID> {

    List<PokemonDocument> findAllByName(String name);

    List<PokemonDocument> getAllBySpeciesName(String name);
}
