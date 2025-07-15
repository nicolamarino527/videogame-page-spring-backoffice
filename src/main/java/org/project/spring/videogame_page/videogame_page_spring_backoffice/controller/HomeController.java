package org.project.spring.videogame_page.videogame_page_spring_backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "pages/home";
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

    @GetMapping("/info")
    public String info() {
        return "pages/info";
    }

    @GetMapping("/access-denied")
    public String denied() {
        return "pages/access-denied";
    }

}
