package com.example.news.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class NewsRequest {
    private String id;
    private String title;
    private String content;
    private String guid;
    private String link;
    private Double grade;
    private List<Source> sources = new ArrayList<Source>();

    public NewsRequest() {}

    public record Source(String id, String name, String description){}
}
