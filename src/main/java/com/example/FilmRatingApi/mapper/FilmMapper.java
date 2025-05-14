package com.example.FilmRatingApi.mapper;

import com.example.FilmRatingApi.model.Film;
import com.example.FilmRatingApi.model.FilmRequestDto;
import com.example.FilmRatingApi.model.FilmResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface FilmMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "ratings", source = "ratings")
    FilmResponseDto toResponse(Film film);

    @Mapping(target = "id", ignore = true)
    Film toEntity(FilmRequestDto filmRequestDto);
}