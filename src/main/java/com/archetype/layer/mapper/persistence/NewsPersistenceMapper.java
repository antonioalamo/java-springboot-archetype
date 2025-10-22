package com.archetype.layer.mapper.persistence;
import com.archetype.layer.domain.model.News;
import com.archetype.layer.persistence.entity.NewsEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;


@Mapper
public interface NewsPersistenceMapper {

    @BeanMapping(ignoreByDefault = false)
    News toDomain(NewsEntity newsEntity);

    @BeanMapping(ignoreByDefault = false)
    NewsEntity fromDomain(News news);
}
