package com.example.FilmRatingApi.repo;

import com.example.FilmRatingApi.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository  extends JpaRepository<Proposal, Long> {
    List<Proposal> findByFilmId(Long filmId);
}
