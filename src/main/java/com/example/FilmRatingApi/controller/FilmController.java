package com.example.FilmRatingApi.controller;

import com.example.FilmRatingApi.model.Film;
import com.example.FilmRatingApi.model.FilmRequestDto;
import com.example.FilmRatingApi.model.FilmResponseDto;
import com.example.FilmRatingApi.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        return new ResponseEntity<>(filmService.getAllFilms(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<FilmResponseDto> getFilmById(@PathVariable Long id) {
        return new ResponseEntity<>(filmService.getFilmById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> addFilm(@RequestBody FilmRequestDto filmRequestDto) {
        filmService.addFilm(filmRequestDto);
        return new ResponseEntity<>("Film added successfully", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilmById(@PathVariable Long id) {
        filmService.deleteFilmById(id);
        return new ResponseEntity<>("Film deleted successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteAllFilms() {
        filmService.deleteAllFilms();
        return new ResponseEntity<>("All films deleted successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFilm(@RequestBody Film film, @PathVariable Long id) {
        filmService.updateFilm(id, film);
        return new ResponseEntity<>("Film updated successfully", HttpStatus.OK);
    }
}
