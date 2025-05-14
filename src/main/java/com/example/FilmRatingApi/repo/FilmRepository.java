package com.example.FilmRatingApi.repo;

import com.example.FilmRatingApi.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    //Film findByTitle(String title);
    Optional<Film> findById(Long id);
}
