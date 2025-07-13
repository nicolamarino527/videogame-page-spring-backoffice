package org.project.spring.videogame_page.videogame_page_spring_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Genre;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.GenreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre getById(Integer id) {
        Optional<Genre> genreAttempt = genreRepository.findById(id);

        if (genreAttempt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with id: " + id);
        }

        return genreAttempt.get();
    }

    public void deleteById(Integer id) {
        genreRepository.deleteById(id);
    }

    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }

    public void delete(Genre genre) {
        deleteById(genre.getId());
    }
}
