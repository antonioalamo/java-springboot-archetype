package com.archetype.layer.persistence.entity;

import com.archetype.layer.domain.model.News;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
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

    public record Source(String id, String name, String description) {}
}