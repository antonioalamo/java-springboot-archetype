package com.archetype.layer.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NewsRequestDTO {
    private String title;
    private String content;
    private String guid;
    private String link;
    private Double grade;
    private List<Source> sources = new ArrayList<Source>();

    public NewsRequestDTO() {}

    public record Source(String id, String name, String description){
    }
}
