package org.project.spring.videogame_page.videogame_page_spring_backoffice.service;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.User;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
}
