package org.project.spring.videogame_page.videogame_page_spring_backoffice.repository;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
