package com.example.FilmRatingApi.controller;

import com.example.FilmRatingApi.model.Rate;
import com.example.FilmRatingApi.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{filmId}")
    public ResponseEntity<List<Rate>> getRatesByFilmId(@PathVariable Long filmId) {
        List<Rate> rates = rateService.findRatesByFilmId(filmId);
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/{filmId}")
    public ResponseEntity<String> addRateToFilm(@PathVariable Long filmId, @RequestBody Rate rate) {
        rateService.addRateToFilm(filmId, rate);
        return new ResponseEntity<>("Rate added successfully", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/avg/{filmId}")
    public ResponseEntity<Long> getAverageRating(@PathVariable Long filmId) {
        Long averageRating = rateService.getAverageRatingByFilmId(filmId);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }
}
