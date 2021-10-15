package ru.otus.lec16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.lec16.service.author.AuthorService;

@RequiredArgsConstructor
@Controller
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(("/author"))
    public String getAll(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "author/authors";
    }
}
