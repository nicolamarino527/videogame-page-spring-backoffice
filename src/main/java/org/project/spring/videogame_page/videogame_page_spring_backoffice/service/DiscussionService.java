package org.project.spring.videogame_page.videogame_page_spring_backoffice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Discussion;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Videogame;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.DiscussionRepository;
import org.springframework.stereotype.Service;

@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final VideogameService videogameService;

    public DiscussionService(DiscussionRepository discussionRepository,
            VideogameService videogameService) {
        this.discussionRepository = discussionRepository;
        this.videogameService = videogameService;
    }

    public Discussion getById(Integer id) {
        return discussionRepository.findById(id).orElse(null);
    }

    public Discussion create(Discussion discussion) {
        Videogame persistentVideogame = videogameService.getById(discussion.getVideogame().getId());
        discussion.setVideogame(persistentVideogame);

        return discussionRepository.save(discussion);
    }

    public void deleteById(Integer id) {
        discussionRepository.deleteById(id);
    }

    public void delete(Discussion discussion) {
        deleteById(discussion.getId());
    }

    public List<Discussion> findByVideogameId(Integer Id) {
        return discussionRepository.findByVideogameId(Id);

    }

    public Discussion createWithVideogame(Discussion discussion, Integer videogameId) {
        Videogame videogame = videogameService.findById(videogameId)
                .orElseThrow(() -> new RuntimeException("Videogame not found"));
        discussion.setVideogame(videogame);
        discussion.setDate(LocalDateTime.now());
        return discussionRepository.save(discussion);
    }
}
