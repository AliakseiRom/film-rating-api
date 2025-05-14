package com.example.FilmRatingApi.service;

import com.example.FilmRatingApi.exception.FilmNotFoundException;
import com.example.FilmRatingApi.model.Film;
import com.example.FilmRatingApi.model.Rate;
import com.example.FilmRatingApi.repo.FilmRepository;
import com.example.FilmRatingApi.repo.RateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private FilmRepository filmRepository;

    public void addRateToFilm(Long filmId, Rate rate) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException("Film not found"));

        rate.setFilm(film);
        rateRepository.save(rate);
    }

    public List<Rate> findRatesByFilmId(Long filmId) {
        return rateRepository.findByFilmId(filmId);
    }

    public Long getAverageRatingByFilmId(Long filmId) {
        List<Rate> rates = rateRepository.findByFilmId(filmId);

        Long averageRating = 0L;
        Long count = 0L;

        for (Rate rate : rates) {
            averageRating += rate.getRate();
            count++;
        }

        averageRating /= count;

        return averageRating;
    }
}
