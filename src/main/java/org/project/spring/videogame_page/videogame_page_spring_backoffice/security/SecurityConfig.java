package org.project.spring.videogame_page.videogame_page_spring_backoffice.security;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    private final UserRepository userRepository;

    SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/videogames/create", "/videogame/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/videogames/**").hasAuthority("ADMIN")
                .requestMatchers("/genres", "/genres/**").hasAuthority("ADMIN")
                .requestMatchers("/platforms", "/platforms/**").hasAuthority("ADMIN")
                .requestMatchers("/videogames", "/videogames/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**").permitAll())

                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/"))
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied"));
        return http.build();

    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
