package org.project.spring.videogame_page.videogame_page_spring_backoffice.controller;

import java.security.Principal;

import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.Discussion;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.model.User;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.repository.UserRepository;
import org.project.spring.videogame_page.videogame_page_spring_backoffice.service.DiscussionService;
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
@RequestMapping("/discussions")
public class DiscussionController {

    private final UserRepository userRepository;
    private final DiscussionService discussionService;

    DiscussionController(DiscussionService discussionService, UserRepository userRepository) {
        this.discussionService = discussionService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create/{videogameId}")
    public String store(
            @PathVariable Integer videogameId,
            @Valid @ModelAttribute("discussion") Discussion formDiscussion,
            BindingResult bindingResult,
            Model model, Principal principal) {

        if (bindingResult.hasErrors()) {

            return "redirect:/videogames/" + videogameId;
        }

        String username = principal.getName();

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        formDiscussion.setUser(user);

        discussionService.createWithVideogame(formDiscussion, videogameId);

        return "redirect:/videogames/" + videogameId;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("discussion", discussionService.getById(id));
        model.addAttribute("edit", true);
        return "discussions/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("discussion") Discussion discussion, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "discussion/create-or-edit";
        }

        discussionService.create(discussion);
        return "redirect:/videogames/" + discussion.getVideogame().getId();
    }
}
