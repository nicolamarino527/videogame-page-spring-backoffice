package org.project.spring.videogame_page.videogame_page_spring_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Videogame;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.VideogameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VideogameService {

    private final VideogameRepository videogameRepository;

    VideogameService(VideogameRepository videogameRepository) {
        this.videogameRepository = videogameRepository;
    }

    public List<Videogame> findAll() {
        return videogameRepository.findAll();
    }

    public Videogame getById(Integer id) {

        Optional<Videogame> videogameAttempt = videogameRepository.findById(id);

        if (videogameAttempt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found with id: " + id);
        }

        return videogameAttempt.get();
    }

    public Videogame create(Videogame videogame) {
        return videogameRepository.save(videogame);
    }

    public Videogame update(Videogame videogame) {
        return videogameRepository.save(videogame);
    }

    public void deleteById(Integer id) {
        videogameRepository.deleteById(id);
    }

    public void delete(Videogame videogame) {
        deleteById(videogame.getId());
    }

    public Optional<Videogame> findById(Integer id) {
        return videogameRepository.findById(id);
    }

    public Boolean existsById(Integer id) {
        return videogameRepository.existsById(id);
    }

    public Boolean existsById(Videogame videogame) {
        return existsById(videogame.getId());
    }

    public List<Videogame> findBySearchingTitle(String title) {
        return videogameRepository.findByTitleContainingIgnoreCase(title);
    }
}
