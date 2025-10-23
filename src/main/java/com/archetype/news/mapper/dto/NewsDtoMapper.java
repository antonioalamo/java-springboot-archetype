package com.archetype.news.mapper.dto;

import com.archetype.news.controller.dto.request.NewsRequest;
import com.archetype.news.controller.dto.response.NewsResponse;
import com.archetype.news.domain.model.News;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;


@Mapper
public interface NewsDtoMapper {

    @BeanMapping(ignoreByDefault = false)
    News toDomain(NewsRequest newsRequestDTO);

    @BeanMapping(ignoreByDefault = false)
    NewsResponse toDto(News news);
}
