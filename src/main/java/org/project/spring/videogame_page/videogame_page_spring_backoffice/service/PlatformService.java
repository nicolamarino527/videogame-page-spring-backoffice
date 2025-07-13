package org.project.spring.videogame_page.videogame_page_spring_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Platform;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.PlatformRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlatformService {

    private final PlatformRepository platformRepository;

    PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    public Platform getById(Integer id) {
        Optional<Platform> platformAttempt = platformRepository.findById(id);

        if (platformAttempt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Platform not found with id: " + id);
        }

        return platformAttempt.get();
    }

    public Platform create(Platform platform) {
        return platformRepository.save(platform);
    }

    public Platform update(Platform platform) {
        return platformRepository.save(platform);
    }

    public void deleteById(Integer id) {
        platformRepository.deleteById(id);
    }

    public void delete(Platform platform) {
        deleteById(platform.getId());
    }
}
