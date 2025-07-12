package org.project.spring.videogame_page.videogame_page_spring_backoffice.repository;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
