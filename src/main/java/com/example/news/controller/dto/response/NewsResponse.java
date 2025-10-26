package com.example.news.controller.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
public class NewsResponse {
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

    public NewsResponse() {}

    public NewsResponse(String id, String title, String content, String guid, String link, Double grade,
                        List<Source> sources) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.guid = guid;
        this.link = link;
        this.grade = grade;
        this.sources = sources;
    }

    public NewsResponse(String errorMessage) {
        this.id = null;
        this.title = null;
        this.content = null;
        this.guid = null;
        this.link = null;
        this.grade = null;
    }



    public record Source(String id, String name, String description){}
}