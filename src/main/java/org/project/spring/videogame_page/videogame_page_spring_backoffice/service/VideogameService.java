package org.project.spring.videogame_page.videogame_page_spring_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Videogame;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.VideogameRepository;
import org.springframework.stereotype.Service;

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

        }

        return videogameAttempt.get();
    }

    public Videogame create(Videogame videogame) {
        return videogameRepository.save(videogame);
    }
}
