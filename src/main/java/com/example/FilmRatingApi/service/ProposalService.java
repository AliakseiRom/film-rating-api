package com.example.FilmRatingApi.service;

import com.example.FilmRatingApi.controller.ProposalController;
import com.example.FilmRatingApi.exception.FilmNotFoundException;
import com.example.FilmRatingApi.model.Film;
import com.example.FilmRatingApi.model.Proposal;
import com.example.FilmRatingApi.repo.FilmRepository;
import com.example.FilmRatingApi.repo.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private FilmRepository filmRepository;

    public void addProposalToFilm(Long filmId, Proposal proposal) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException("Film not found"));
        proposal.setFilm(film);
        proposalRepository.save(proposal);
    }

    public List<Proposal> getAllProposals(Long filmId) {
        return proposalRepository.findByFilmId(filmId);
    }
}
