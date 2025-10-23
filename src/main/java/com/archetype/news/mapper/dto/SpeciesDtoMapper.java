package com.archetype.news.mapper.dto;

import com.archetype.news.controller.dto.response.SpeciesResponse;
import com.archetype.news.domain.model.Species;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SpeciesDtoMapper {

    SpeciesResponse toDto(Species species);

    List<SpeciesResponse> toDto(List<Species> species);
}
