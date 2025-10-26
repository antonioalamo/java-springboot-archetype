package com.example.news.mapper.dto;

import com.example.news.controller.dto.response.SpeciesResponse;
import com.example.news.domain.model.Species;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SpeciesDtoMapper {

    SpeciesResponse toDto(Species species);

    List<SpeciesResponse> toDto(List<Species> species);
}
