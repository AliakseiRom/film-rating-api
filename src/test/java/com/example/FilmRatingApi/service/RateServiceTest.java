package com.example.FilmRatingApi.service;

import com.example.FilmRatingApi.exception.FilmNotFoundException;
import com.example.FilmRatingApi.model.Film;
import com.example.FilmRatingApi.model.Rate;
import com.example.FilmRatingApi.repo.FilmRepository;
import com.example.FilmRatingApi.repo.RateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RateServiceTest {

    @Mock
    private RateRepository rateRepository;

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private RateService rateService;

    private Film film;
    private Rate rate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        film = new Film();
        film.setId(1L);
        film.setTitle("Test Film");

        rate = new Rate();
        rate.setId(1L);
        rate.setRate(4L);
    }

    @Test
    void testAddRateToFilmSuccess() {
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        rateService.addRateToFilm(1L, rate);

        assertEquals(film, rate.getFilm());
        verify(rateRepository).save(rate);
    }

    @Test
    void testAddRateToFilmThrowsIfFilmNotFound() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FilmNotFoundException.class, () -> rateService.addRateToFilm(1L, rate));
        verify(rateRepository, never()).save(any());
    }

    @Test
    void testFindRatesByFilmId() {
        List<Rate> rates = Arrays.asList(new Rate(), new Rate());

        when(rateRepository.findByFilmId(1L)).thenReturn(rates);

        List<Rate> result = rateService.findRatesByFilmId(1L);

        assertEquals(2, result.size());
        verify(rateRepository).findByFilmId(1L);
    }

    @Test
    void testGetAverageRatingByFilmId() {
        Rate rate1 = new Rate();
        rate1.setRate(4L);

        Rate rate2 = new Rate();
        rate2.setRate(2L);

        when(rateRepository.findByFilmId(1L)).thenReturn(List.of(rate1, rate2));

        Long avg = rateService.getAverageRatingByFilmId(1L);

        assertEquals(3L, avg);
        verify(rateRepository).findByFilmId(1L);
    }

    @Test
    void testGetAverageRatingByFilmIdEmptyList() {
        when(rateRepository.findByFilmId(1L)).thenReturn(Collections.emptyList());

        assertThrows(ArithmeticException.class, () -> rateService.getAverageRatingByFilmId(1L));
    }
}
