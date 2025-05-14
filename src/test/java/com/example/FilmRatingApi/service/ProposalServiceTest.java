package com.example.FilmRatingApi.service;

import com.example.FilmRatingApi.exception.FilmNotFoundException;
import com.example.FilmRatingApi.model.Film;
import com.example.FilmRatingApi.model.Proposal;
import com.example.FilmRatingApi.repo.FilmRepository;
import com.example.FilmRatingApi.repo.ProposalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProposalServiceTest {

    @Mock
    private ProposalRepository proposalRepository;

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private ProposalService proposalService;

    private Film film;
    private Proposal proposal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        film = new Film();
        film.setId(1L);
        film.setTitle("Test Film");

        proposal = new Proposal();
        proposal.setId(1L);
        proposal.setLink("https://example.com/proposal");
    }

    @Test
    void testAddProposalToFilmSuccess() {
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        proposalService.addProposalToFilm(1L, proposal);

        assertEquals(film, proposal.getFilm());
        verify(proposalRepository).save(proposal);
    }

    @Test
    void testAddProposalToFilmThrowsExceptionIfFilmNotFound() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FilmNotFoundException.class, () -> proposalService.addProposalToFilm(1L, proposal));
        verify(proposalRepository, never()).save(any());
    }

    @Test
    void testGetAllProposals() {
        List<Proposal> proposals = Arrays.asList(
                new Proposal(), new Proposal()
        );
        when(proposalRepository.findByFilmId(1L)).thenReturn(proposals);

        List<Proposal> result = proposalService.getAllProposals(1L);

        assertEquals(2, result.size());
        verify(proposalRepository).findByFilmId(1L);
    }
}
