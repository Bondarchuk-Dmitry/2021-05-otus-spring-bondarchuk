package ru.otus.lec16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.lec16.service.genre.GenreService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("genres", genreService.getAll());
        return "genre/genres";
    }
}
