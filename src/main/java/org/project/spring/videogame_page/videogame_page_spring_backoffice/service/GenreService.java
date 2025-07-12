package org.project.spring.videogame_page.videogame_page_spring_backoffice.service;

import java.util.List;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Genre;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
