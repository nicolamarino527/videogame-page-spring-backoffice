package org.project.spring.videogame_page.videogame_page_spring_backoffice.controller;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Genre;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Videogame;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "genre/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("genre", new Genre());

        return "genre/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("genre") Genre genre,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "genre/create-or-edit";
        }

        genreService.create(genre);

        model.addAttribute("genre", new Genre());
        return "redirect:/genres";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("genre", genreService.getById(id));
        model.addAttribute("edit", true);

        return "genre/create-or-edit";
    }

    @PostMapping("edit/{id}")
    public String edit(@Valid @ModelAttribute("genre") Genre genre, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "/genre/edit";
        }
        genreService.update(genre);

        return "redirect:/genres";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Genre genreToDelete = genreService.getById(id);

        for (Videogame linkedVideogame : genreToDelete.getVideogames()) {
            linkedVideogame.getGenres().remove(genreToDelete);
        }

        genreService.delete(genreToDelete);

        return "redirect:/genres";
    }

}
