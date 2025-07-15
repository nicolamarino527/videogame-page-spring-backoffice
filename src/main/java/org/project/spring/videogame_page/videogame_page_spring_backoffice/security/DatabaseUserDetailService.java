package org.project.spring.videogame_page.videogame_page_spring_backoffice.security;

import java.util.Optional;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.User;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DatabaseUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    DatabaseUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt = userRepository.findByUserName(username);

        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("No user found for: " + username);
        }

        return new DatabaseUserDetail(userAttempt.get());
    }

}
