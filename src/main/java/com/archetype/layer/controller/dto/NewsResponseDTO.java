package com.archetype.layer.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.util.List;
import java.util.ArrayList;

public class NewsResponseDTO {
    private String id;

    @NotNull(message = "Title cannot be null")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotNull(message = "Content cannot be null")
    private String content;

    @NotNull(message = "GUID cannot be null")
    private String guid;

    @NotNull(message = "Link cannot be null")
    private String link;

    @NotNull(message = "Grade cannot be null")
    @Min(value = 1, message = "Grade must be at least 1")
    @Max(value = 10, message = "Grade cannot exceed 10")
    private Double grade;

    private List<Source> sources = new ArrayList<Source>();

    private String errorMessage;

    public NewsResponseDTO() {}

    public NewsResponseDTO(String id, String title, String content, String guid, String link, Double grade,
                           List<Source> sources) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.guid = guid;
        this.link = link;
        this.grade = grade;
        this.sources = sources;
        this.errorMessage = null;
    }

    public NewsResponseDTO(String errorMessage) {
        this.id = null;
        this.title = null;
        this.content = null;
        this.guid = null;
        this.link = null;
        this.grade = null;
        this.errorMessage = errorMessage;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getGuid() { return guid; }
    public void setGuid(String guid) { this.guid = guid; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public Double getGrade() { return grade; }
    public void setGrade(Double grade) { this.grade = grade; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public List<NewsResponseDTO.Source> getSources() {
        return sources;
    }

    public void setSources(List<NewsResponseDTO.Source> sources) {
        this.sources.addAll(sources);
    }

    public record Source(String id, String name, String description){
    }
}