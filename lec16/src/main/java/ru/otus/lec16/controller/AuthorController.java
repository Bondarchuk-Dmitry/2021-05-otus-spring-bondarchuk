package ru.otus.lec16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.lec16.service.author.AuthorService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "author/authors";
    }
}
