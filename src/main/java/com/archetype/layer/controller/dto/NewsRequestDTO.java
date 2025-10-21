package com.archetype.layer.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class NewsRequestDTO {
    private String title;
    private String content;
    private String guid;
    private String link;
    private Double grade;
    private List<Source> sources = new ArrayList<Source>();

    public NewsRequestDTO() {}

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

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources.addAll(sources);
    }


    public record Source(String id, String name, String description){
    }
}
