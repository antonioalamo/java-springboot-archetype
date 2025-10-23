package com.archetype.news.mapper.persistence;
import com.archetype.news.domain.model.News;
import com.archetype.news.persistence.document.NewsDocument;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;


@Mapper
public interface NewsPersistenceMapper {

    @BeanMapping(ignoreByDefault = false)
    News toDomain(NewsDocument newsEntity);

    @BeanMapping(ignoreByDefault = false)
    NewsDocument fromDomain(News news);
}
