package com.example.news.controller;

import com.example.news.controller.dto.request.NewsRequest;
import com.example.news.controller.dto.response.NewsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "News API", description = "API for managing news articles")
@RestController
@RequestMapping("/api/news")
public interface NewsControllerInfo {

    @Operation(summary = "Create a new news article", description = "Creates a news article and returns the created article with its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "News article created successfully",
                    content = @Content(schema = @Schema(implementation = NewsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = NewsResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = NewsResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    NewsResponse create(@Valid @RequestBody NewsRequest request);

    @Operation(summary = "Get a news article by ID", description = "Retrieves a news article by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News article found",
                    content = @Content(schema = @Schema(implementation = NewsResponse.class))),
            @ApiResponse(responseCode = "404", description = "News article not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    NewsResponse getById(@Parameter(description = "ID of the news article") @PathVariable String id);

    @Operation(summary = "List all news articles", description = "Retrieves a list of all news articles.")
    @ApiResponse(responseCode = "200", description = "List of news articles",
            content = @Content(schema = @Schema(implementation = NewsResponse.class)))
    @GetMapping
    List<NewsResponse> list();

    @Operation(summary = "Update a news article", description = "Updates an existing news article by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News article updated successfully",
                    content = @Content(schema = @Schema(implementation = NewsResponse.class))),
            @ApiResponse(responseCode = "404", description = "News article not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    NewsResponse update(@Parameter(description = "ID of the news article") @PathVariable String id,
                        @Valid @RequestBody NewsRequest request);

    @Operation(summary = "Delete a news article", description = "Deletes a news article by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "News article deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "News article not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@Parameter(description = "ID of the news article") @PathVariable String id);
}