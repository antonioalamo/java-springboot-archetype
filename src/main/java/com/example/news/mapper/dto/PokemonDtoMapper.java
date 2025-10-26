package com.example.news.mapper.dto;

import com.example.news.controller.dto.request.PokemonCreate;
import com.example.news.controller.dto.response.PokemonDetails;
import com.example.news.domain.model.Pokemon;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PokemonDtoMapper {

    @BeanMapping(ignoreByDefault = true)
    Pokemon toDomain(PokemonCreate pokemonCreateRequest);



    @Mapping(target = "secret.attackIV", source = "pokemon.attackIV")
    @Mapping(target = "secret.defenseIV", source = "pokemon.defenseIV")
    @Mapping(target = "secret.specialIV", source = "pokemon.specialIV")
    @Mapping(target = "secret.speedIV", source = "pokemon.speedIV")
    @Mapping(target = "secret.hpIV", source = "pokemon.hpIV")
    @Mapping(target = "nationalId", source = "pokemon.species.nationalId")
    @Mapping(target = "moves", expression = "java(pokemon.getMoves())")
    @Mapping(target = "firstType", source = "pokemon.species.firstType.element")
    @Mapping(target = "secondType", source = "pokemon.species.secondType.element")
    @Mapping(target = "species", source = "pokemon.species.name")
    PokemonDetails toDto(Pokemon pokemon);
}
