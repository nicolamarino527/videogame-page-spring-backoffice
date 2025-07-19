package org.project.spring.videogame_page.videogame_page_spring_backoffice.controller;

import java.util.List;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Discussion;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Videogame;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.DiscussionService;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.GenreService;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.PlatformService;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.VideogameService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/videogames")
public class VideogameController {

    private final PlatformService platformService;
    private final VideogameService videogameService;
    private final GenreService genreService;
    private final DiscussionService discussionService;

    public VideogameController(PlatformService platformService,
            VideogameService videogameService, GenreService genreService,
            DiscussionService discussionService) {
        this.platformService = platformService;
        this.videogameService = videogameService;
        this.genreService = genreService;
        this.discussionService = discussionService;
    }

    @GetMapping
    public String index(Authentication authentication, Model model) {
        List<Videogame> videogames = videogameService.findAll();
        model.addAttribute("videogames", videogames);
        model.addAttribute("username", authentication.getName());

        return "videogame/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer gameId) {
        Videogame videogame = videogameService.getById(gameId);
        model.addAttribute("videogame", videogame);

        List<Discussion> discussions = discussionService.findByVideogameId(gameId);
        model.addAttribute("discussions", discussions);

        Discussion newDiscussion = new Discussion();
        newDiscussion.setVideogame(videogame);
        model.addAttribute("discussion", newDiscussion);

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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        videogameService.deleteById(id);

        return "redirect:/videogames";
    }

    @GetMapping("/search")
    public String searchByTitle(@RequestParam("title") String name, Model model) {
        List<Videogame> videogames = videogameService.findBySearchingTitle(name);
        model.addAttribute("videogames", videogames);
        return "videogame/index"; // correggi anche qui, "videogame" non "videogames"
    }

    // @GetMapping("/{id}/discussion")
    // public String showDiscussions(@PathVariable("id") Integer id, Model model) {
    // var videogame = videogameService.getById(id);

    // model.addAttribute("videogame", videogame);
    // model.addAttribute("discussions", videogame.getDiscussions());
    // model.addAttribute("discussion", new Discussion());

    // return "discussion/create-or-edit";

    // }
}
