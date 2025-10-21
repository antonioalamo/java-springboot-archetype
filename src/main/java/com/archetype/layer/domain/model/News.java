package com.archetype.layer.domain.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class News {
    private String id;
    private String title;
    private String content;
    private String guid;
    private String link;
    private Double grade;
    private final List<Source> sources = new ArrayList<>();

    public News(String id, String title, String content, String guid, String link, Double grade, List<Source> sources) {

        validateTitle(title);
        validateLink(link);
        validateGrade(grade);
        validateContent(content);
        validateGuid(guid);

        this.id = id;
        this.title = title;
        this.content = content;
        this.guid = guid;
        this.link = link;
        this.grade = grade;
        this.sources.addAll(sources);
    }

    private void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (title.length() > 100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");
        }
    }

    private void validateContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
    }

    private void validateLink(String link) {
        if (link == null || link.isEmpty()) {
            throw new IllegalArgumentException("Link cannot be null or empty");
        }
        try {
            new URI(link);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URL format for link");
        }
    }

    private void validateGrade(Double grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null");
        }
        if (grade < 1 || grade > 10) {
            throw new IllegalArgumentException("Grade must be between 1 and 10");
        }
    }

    // New validator: numeric string without decimals, up to 12 digits
    private void validateGuid(String guid) {
        if (guid == null || guid.isEmpty()) {
            throw new IllegalArgumentException("GUID cannot be null or empty");
        }
        if (!guid.matches("^\\d{1,12}$")) {
            throw new IllegalArgumentException("GUID must be a numeric value without decimals and at most 12 digits");
        }
    }

    public boolean isHighImportance() {
        return grade != null && grade >= 8;
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return title + ": " + content.substring(0, Math.min(100, content.length())) + "...";
    }

    public String getTitle() { return title; }

    public void changeTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public String getContent() { return content; }
    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        this.content = content;
    }

    public String getGuid() { return guid; }
    public void setGuid(String guid) {
        validateGuid(guid);
        this.guid = guid;
    }

    public String getLink() { return link; }
    public void setLink(String link) {
        validateLink(link);
        this.link = link;
    }

    public Double getGrade() { return grade; }
    public void setGrade(Double grade) {
        validateGrade(grade);
        this.grade = grade;
    }

    public List<Source> getSources() { return List.copyOf(sources); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    public record Source(String id, String name, String description){
    }
}
