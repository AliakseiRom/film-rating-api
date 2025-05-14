package com.example.FilmRatingApi.controller;

import com.example.FilmRatingApi.model.Proposal;
import com.example.FilmRatingApi.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propose")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{filmId}")
    public ResponseEntity<String> addProposal(@PathVariable Long filmId, @RequestBody Proposal proposal) {
        proposalService.addProposalToFilm(filmId, proposal);
        return new ResponseEntity<>("Successfully added proposal to the film", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{filmId}")
    public ResponseEntity<List<Proposal>> getAllProposals(@PathVariable Long filmId) {
        return new ResponseEntity<>(proposalService.getAllProposals(filmId), HttpStatus.OK);
    }
}
