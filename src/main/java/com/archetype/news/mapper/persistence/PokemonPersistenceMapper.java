package com.archetype.news.mapper.persistence;

import com.archetype.news.domain.model.Ability;
import com.archetype.news.domain.model.Pokemon;
import com.archetype.news.persistence.document.AbilityEmbedded;
import com.archetype.news.persistence.document.PokemonDocument;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PokemonPersistenceMapper {


    @Mapping(target = "types", expression = "java(pokemon.getSpecies().typesAsString())")
    @Mapping(target = "speciesName", source = "pokemon.species.name")
    @Mapping(target = "nationalId", source = "pokemon.species.nationalId")
    @Mapping(target = "id", expression = "java(new PokemonId())")
    @Mapping(target = "abilities", source = "pokemon.species.abilities")
    PokemonDocument toDocument(Pokemon pokemon);


    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "species", ignore = true)
    Pokemon toDomain(PokemonDocument doc);

    List<Pokemon> toDomain(List<PokemonDocument> docs);

    AbilityEmbedded toDocument(Ability ability);

    @Mapping(target = "description", expression="java(new String())")
    Ability toDomain(AbilityEmbedded ability);
}
