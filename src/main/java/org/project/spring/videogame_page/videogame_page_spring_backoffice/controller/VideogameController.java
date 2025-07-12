package org.project.spring.videogame_page.videogame_page_spring_backoffice.controller;

import java.util.List;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Videogame;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.GenreService;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.PlatformService;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.VideogameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/videogames")
public class VideogameController {

    private PlatformService platformService;
    private VideogameService videogameService;
    GenreService genreService;

    public VideogameController(PlatformService platformService,
            VideogameService videogameService, GenreService genreService) {
        this.platformService = platformService;
        this.videogameService = videogameService;
        this.genreService = genreService;
    }

    @GetMapping
    public String index(Model model) {
        List<Videogame> videogames = videogameService.findAll();
        model.addAttribute("videogames", videogames);

        return "videogame/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer gameId) {
        Videogame videogame = videogameService.getById(gameId);
        model.addAttribute("videogame", videogame);

        return "videogame/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("videogame", new Videogame());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("platforms", platformService.findAll());

        return "videogame/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("videogame") Videogame videogame,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("platforms", platformService.findAll());
            return "videogame/create-or-edit";
        }

        videogameService.create(videogame);
        return "redirect:/videogames";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("platforms", platformService.findAll());
        model.addAttribute("edit", true);
        model.addAttribute("videogame", videogameService.getById(id));

        return "videogame/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("videogame") Videogame videogame, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("platforms", platformService.findAll());

            return "videogame/create-or-edit";
        }

        videogameService.update(videogame);
        return "redirect:/videogames";
    }

}
