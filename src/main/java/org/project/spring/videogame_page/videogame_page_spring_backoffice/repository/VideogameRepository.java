package org.project.spring.videogame_page.videogame_page_spring_backoffice.repository;

import java.util.List;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
    List<Videogame> findByTitleContainingIgnoreCase(String title);
}
