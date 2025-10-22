package com.archetype.layer.controller;

import com.archetype.layer.controller.dto.NewsRequestDTO;
import com.archetype.layer.controller.dto.NewsResponseDTO;
import com.archetype.layer.mapper.dto.NewsDtoMapper;
import com.archetype.layer.service.exception.NewsNotFoundException;
import com.archetype.layer.domain.model.News;
import com.archetype.layer.service.NewsService;
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
    public NewsResponseDTO create(@Valid @RequestBody NewsRequestDTO request) {

        News newsToCreate = dtoMapper.toDomain(request);
        News createdNews = service.create(newsToCreate);

        return dtoMapper.toDto(createdNews);

    }

    @GetMapping("/{id}")
    public NewsResponseDTO getById(@PathVariable String id) {
        return service.getById(id)
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new NewsNotFoundException("News with id " + id + " not found"));
    }

    @GetMapping
    public List<NewsResponseDTO> list() {
        return service.list().stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public NewsResponseDTO update(@PathVariable String id, @RequestBody NewsRequestDTO request) {
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

