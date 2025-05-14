package com.example.FilmRatingApi.repo;

import com.example.FilmRatingApi.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findByFilmId(Long filmId);
}
