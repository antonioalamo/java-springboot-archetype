package com.example.news.mapper.persistence;
import com.example.news.domain.model.News;
import com.example.news.persistence.document.NewsDocument;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;


@Mapper
public interface NewsPersistenceMapper {

    @BeanMapping(ignoreByDefault = false)
    News toDomain(NewsDocument newsEntity);

    @BeanMapping(ignoreByDefault = false)
    NewsDocument fromDomain(News news);
}
