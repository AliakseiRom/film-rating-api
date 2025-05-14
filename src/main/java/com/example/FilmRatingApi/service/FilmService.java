package com.example.FilmRatingApi.service;


import com.example.FilmRatingApi.exception.FilmNotFoundException;
import com.example.FilmRatingApi.mapper.FilmMapper;
import com.example.FilmRatingApi.model.Film;
import com.example.FilmRatingApi.model.FilmRequestDto;
import com.example.FilmRatingApi.model.FilmResponseDto;
import com.example.FilmRatingApi.repo.FilmRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmMapper filmMapper;

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public FilmResponseDto getFilmById(Long id) {
        return filmMapper.toResponse(filmRepository.findById(id).get());
    }

    public void addFilm(FilmRequestDto filmRequestDto) {
        filmRepository.save(filmMapper.toEntity(filmRequestDto));
    }

    public FilmResponseDto updateFilm(Long filmId, Film film) {
        Optional<Film> filmOptional = filmRepository.findById(filmId);
        if (filmOptional.isPresent()) {
            Film filmToUpdate = filmOptional.get();
            if (filmRepository.existsById(filmId)) {
                filmToUpdate.setTitle(film.getTitle());
                Film updatedFilm = filmRepository.save(filmToUpdate);
                return filmMapper.toResponse(updatedFilm);
            }
        }
        throw new FilmNotFoundException("Film not found");
    }

    public void deleteAllFilms() {
        filmRepository.deleteAll();
    }

    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }
}
