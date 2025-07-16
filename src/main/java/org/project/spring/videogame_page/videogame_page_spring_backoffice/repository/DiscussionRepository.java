package org.project.spring.videogame_page.videogame_page_spring_backoffice.repository;

import java.util.List;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionRepository extends JpaRepository<Discussion, Integer> {

    List<Discussion> findByVideogameId(Integer id);

}
