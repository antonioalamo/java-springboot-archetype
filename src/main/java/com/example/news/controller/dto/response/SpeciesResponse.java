package com.example.news.controller.dto.response;

import com.example.news.domain.model.Ability;
import com.example.news.domain.model.Species;
import com.example.news.domain.model.Type;

import java.util.List;
import java.util.Map;

public record SpeciesResponse(int nationalId,
                              String name,
                              Type firstType,
                              Type secondType,
                              List<Ability> abilities,
                              Map<Integer, String> moves,
                              Species.PokemonStats stats) {
}
