package ru.otus.lec16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.lec16.service.genre.GenreService;

@RequiredArgsConstructor
@Controller
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre")
    public String getAll(Model model) {
        model.addAttribute("genres", genreService.getAll());
        return "genre/genres";
    }
}
