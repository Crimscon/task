package com.test.task.controller;

import com.test.task.model.User;
import com.test.task.service.AuthorService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private final AuthorService authorService;

    public MainController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getMainPage(Model model, @AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("authorsFromModel", authorService.getAllAuthors());
            return "index";
        } else return "redirect:/login";
    }
}
