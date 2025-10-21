package com.archetype.layer.controller;

import com.archetype.layer.controller.dto.NewsRequestDTO;
import com.archetype.layer.controller.dto.NewsResponseDTO;
import com.archetype.layer.service.exception.NewsNotFoundException;
import com.archetype.layer.domain.model.News;
import com.archetype.layer.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
public class NewsController implements NewsControllerInfo {

    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsResponseDTO create(@Valid @RequestBody NewsRequestDTO request) {

        News newsToCreate = toDomain(request, null);
        News createdNews = service.create(newsToCreate);

        return toDto(createdNews);

    }

    @GetMapping("/{id}")
    public NewsResponseDTO getById(@PathVariable String id) {
        return service.getById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NewsNotFoundException("News with id " + id + " not found"));
    }

    @GetMapping
    public List<NewsResponseDTO> list() {
        return service.list().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public NewsResponseDTO update(@PathVariable String id, @RequestBody NewsRequestDTO request) {
        News updates = toDomain(request, id);
        return service.update(id, updates)
                .map(this::toDto)
                .orElseThrow(() -> new NewsNotFoundException("News with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    private NewsResponseDTO toDto(News news) {

        List<NewsResponseDTO.Source> sources = new ArrayList<>();
        for (News.Source source : news.getSources()) {
            sources.add(
                    new NewsResponseDTO.Source(source.id(), source.name(), source.description()));
        }

        return new NewsResponseDTO(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getGuid(),
                news.getLink(),
                news.getGrade(),
                sources
        );
    }

    private News toDomain(NewsRequestDTO request, String id) {

        List<News.Source> sources = new ArrayList<News.Source>();
        for (NewsRequestDTO.Source sourceDTO : request.getSources()) {
            sources.add(
                    new News.Source(sourceDTO.id(), sourceDTO.name(), sourceDTO.name()));
        }
        return new News(
                null,
                request.getTitle(),
                request.getContent(),
                request.getGuid(),
                request.getLink(),
                request.getGrade(),
                sources
        );
    }

}

