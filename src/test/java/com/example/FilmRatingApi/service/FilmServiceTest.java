package com.example.FilmRatingApi.service;

import com.example.FilmRatingApi.exception.FilmNotFoundException;
import com.example.FilmRatingApi.mapper.FilmMapper;
import com.example.FilmRatingApi.model.Film;
import com.example.FilmRatingApi.model.FilmRequestDto;
import com.example.FilmRatingApi.model.FilmResponseDto;
import com.example.FilmRatingApi.repo.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmMapper filmMapper;

    @InjectMocks
    private FilmService filmService;

    private Film film;
    private FilmRequestDto requestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        film = new Film();
        film.setId(1L);
        film.setTitle("Test Film");

        requestDto = FilmRequestDto.builder()
                .setTitle("Test Film")
                .setRatings(Collections.emptyList())
                .build();
    }

    @Test
    void testGetAllFilms() {
        List<Film> films = Arrays.asList(new Film(), new Film());
        when(filmRepository.findAll()).thenReturn(films);

        List<Film> result = filmService.getAllFilms();

        assertEquals(2, result.size());
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    void testGetFilmById() {
        FilmResponseDto dto = new FilmResponseDto(1L, "Test", null);

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(filmMapper.toResponse(film)).thenReturn(dto);

        FilmResponseDto result = filmService.getFilmById(1L);

        assertEquals(dto.id(), result.id());
        verify(filmRepository).findById(1L);
        verify(filmMapper).toResponse(film);
    }

    @Test
    void testAddFilm() {
        when(filmMapper.toEntity(requestDto)).thenReturn(film);

        filmService.addFilm(requestDto);

        verify(filmMapper).toEntity(requestDto);
        verify(filmRepository).save(film);
    }

    @Test
    void testUpdateFilmSuccess() {
        Film inputFilm = new Film();
        inputFilm.setTitle("New");

        Film updatedFilm = new Film();
        updatedFilm.setId(1L);
        updatedFilm.setTitle("New");

        FilmResponseDto responseDto = new FilmResponseDto(1L, "New", null);

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(filmRepository.existsById(1L)).thenReturn(true);
        when(filmRepository.save(film)).thenReturn(updatedFilm);
        when(filmMapper.toResponse(updatedFilm)).thenReturn(responseDto);

        FilmResponseDto result = filmService.updateFilm(1L, inputFilm);

        assertEquals("New", result.title());
        verify(filmRepository).save(film);
    }

    @Test
    void testUpdateFilmNotFound() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FilmNotFoundException.class, () -> filmService.updateFilm(1L, new Film()));
    }

    @Test
    void testDeleteAllFilms() {
        filmService.deleteAllFilms();
        verify(filmRepository).deleteAll();
    }

    @Test
    void testDeleteFilmById() {
        filmService.deleteFilmById(1L);
        verify(filmRepository).deleteById(1L);
    }
}
