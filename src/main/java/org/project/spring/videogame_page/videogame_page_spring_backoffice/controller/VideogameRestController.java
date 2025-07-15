package org.project.spring.videogame_page.videogame_page_spring_backoffice.controller;

import java.util.List;
import java.util.Optional;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Videogame;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.VideogameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videogames")
public class VideogameRestController {

    private final VideogameService videogameService;

    VideogameRestController(VideogameService videogameService) {
        this.videogameService = videogameService;
    }

    @GetMapping
    public List<Videogame> index() {
        List<Videogame> videogames = videogameService.findAll();
        return videogames;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videogame> show(@PathVariable Integer id) {
        Optional<Videogame> videogameAttempt = videogameService.findById(id);

        if (videogameAttempt.isEmpty()) {
            return new ResponseEntity<Videogame>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Videogame>(videogameAttempt.get(), HttpStatus.OK);

    }
}
