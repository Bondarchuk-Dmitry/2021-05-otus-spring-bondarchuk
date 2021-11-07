package ru.otus.lec23.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lec23.domain.Author;
import ru.otus.lec23.service.author.AuthorService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("api/authors")
    public List<Author> getAll() {
        return authorService.getAll();
    }
}
