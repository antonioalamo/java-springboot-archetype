package com.archetype.layer.mapper.dto;

import com.archetype.layer.controller.dto.NewsRequestDTO;
import com.archetype.layer.controller.dto.NewsResponseDTO;
import com.archetype.layer.domain.model.News;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface NewsDtoMapper {

    @BeanMapping(ignoreByDefault = false)
    News toDomain(NewsRequestDTO newsRequestDTO);

    @BeanMapping(ignoreByDefault = false)
    NewsResponseDTO toDto(News news);
}
