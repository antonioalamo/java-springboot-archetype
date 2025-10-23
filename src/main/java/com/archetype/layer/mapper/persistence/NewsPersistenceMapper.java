package com.archetype.layer.mapper.persistence;
import com.archetype.layer.domain.model.News;
import com.archetype.layer.persistence.document.NewsDocument;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;


@Mapper
public interface NewsPersistenceMapper {

    @BeanMapping(ignoreByDefault = false)
    News toDomain(NewsDocument newsEntity);

    @BeanMapping(ignoreByDefault = false)
    NewsDocument fromDomain(News news);
}
