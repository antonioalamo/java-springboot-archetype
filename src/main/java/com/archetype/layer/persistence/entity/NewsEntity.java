package com.archetype.layer.persistence.entity;

import com.archetype.layer.domain.model.News;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "news")
public class NewsEntity {

    @Id
    private String id;
    private String title;
    private String content;
    private String guid;
    private String link;
    private Double grade;
    private List<Source> sources = new ArrayList<>();

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getGuid() {
        return guid;
    }

    public static NewsEntity fromDomain(News news) {
        NewsEntity entity = new NewsEntity();

        entity.id = UUID.randomUUID().toString();
        entity.title = news.getTitle();
        entity.content = news.getContent();
        entity.guid = news.getGuid();
        entity.link = news.getLink();
        entity.grade = news.getGrade();
        entity.sources.addAll(
                news.getSources().stream()
                        .map(source -> new Source(source.id(), source.name(), source.description()))
                        .toList()
        );

        return entity;
    }

    public News toDomain() {

        return new News(
                this.id,
                this.title,
                this.content,
                this.guid,
                this.link,
                this.grade,
                this.sources.stream()
                        .map(source -> new News.Source(source.id, source.name, source.description))
                        .toList()
        );
    }

    public record Source(String id, String name, String description) {}
}