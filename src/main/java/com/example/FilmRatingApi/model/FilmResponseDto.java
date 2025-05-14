package com.example.FilmRatingApi.model;

import lombok.Builder;

import java.util.List;

@Builder(setterPrefix = "set", toBuilder = true)
public record FilmResponseDto(
        Long id,
        String title,
        List<Rate> ratings
) {
}
