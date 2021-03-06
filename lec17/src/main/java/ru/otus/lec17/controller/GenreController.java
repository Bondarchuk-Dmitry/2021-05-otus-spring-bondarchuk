package ru.otus.lec17.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.otus.lec17.domain.Genre;
import ru.otus.lec17.service.genre.GenreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GenreController {

    private final GenreService genreService;

    @GetMapping("api/genres")
    public List<Genre> getAll() {
        return genreService.getAll();
    }
}
