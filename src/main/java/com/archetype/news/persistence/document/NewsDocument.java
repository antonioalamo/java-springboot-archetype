package com.archetype.news.persistence.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "news")
public class NewsDocument {

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