package org.project.spring.videogame_page.videogame_page_spring_backoffice.controller;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Platform;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Videogame;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.PlatformService;
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
@RequestMapping("/platforms")
public class PlatformController {

    private final PlatformService platformService;

    PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("platforms", platformService.findAll());
        return "platform/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("platform", new Platform());

        return "platform/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("platform") Platform platform,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "platform/create-or-edit";
        }

        platformService.create(platform);

        model.addAttribute("platform", new Platform());
        return "redirect:/platforms";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("platform", platformService.getById(id));
        model.addAttribute("edit", true);

        return "platform/create-or-edit";
    }

    @PostMapping("edit/{id}")
    public String edit(@Valid @ModelAttribute("platform") Platform platform, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "/platform/edit";
        }
        platformService.update(platform);

        return "redirect:/platforms";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Platform platformToDelete = platformService.getById(id);

        for (Videogame linkedVideogame : platformToDelete.getVideogames()) {
            linkedVideogame.getPlatforms().remove(platformToDelete);
        }

        platformService.delete(platformToDelete);

        return "redirect:/platforms";
    }

}
