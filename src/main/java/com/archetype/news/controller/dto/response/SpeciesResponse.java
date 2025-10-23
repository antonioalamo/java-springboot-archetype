package com.archetype.news.controller.dto.response;

import com.archetype.news.domain.model.Ability;
import com.archetype.news.domain.model.Species;
import com.archetype.news.domain.model.Type;

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
