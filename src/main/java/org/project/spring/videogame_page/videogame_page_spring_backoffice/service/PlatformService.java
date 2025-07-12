package org.project.spring.videogame_page.videogame_page_spring_backoffice.service;

import java.util.List;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Platform;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.PlatformRepository;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {

    private final PlatformRepository platformRepository;

    PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

}
