package com.example.FilmRatingApi.model;

import lombok.Builder;

import java.util.List;

@Builder(setterPrefix = "set", toBuilder = true)
public record FilmRequestDto(
        String title,
        List<Rate> ratings
) {
}
