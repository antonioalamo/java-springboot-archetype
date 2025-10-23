package com.archetype.news.controller;

import com.archetype.news.controller.dto.request.NewsRequest;
import com.archetype.news.controller.dto.response.NewsResponse;
import com.archetype.news.mapper.dto.NewsDtoMapper;
import com.archetype.news.service.exception.NewsNotFoundException;
import com.archetype.news.domain.model.News;
import com.archetype.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController implements NewsControllerInfo {

    private final NewsService service;
    private final NewsDtoMapper dtoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsResponse create(@Valid @RequestBody NewsRequest request) {


        return dtoMapper.toDto(service.create( dtoMapper.toDomain(request)));

    }

    @GetMapping("/{id}")
    public NewsResponse getById(@PathVariable String id) {
        return service.getById(id)
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new NewsNotFoundException("News with id " + id + " not found"));
    }

    @GetMapping
    public List<NewsResponse> list() {
        return service.list().stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public NewsResponse update(@PathVariable String id, @RequestBody NewsRequest request) {
        News updates =  dtoMapper.toDomain(request);

        return dtoMapper.toDto(service.update(id, updates).orElseThrow(()
                -> new NewsNotFoundException("News with id " + id + " not found")));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}

