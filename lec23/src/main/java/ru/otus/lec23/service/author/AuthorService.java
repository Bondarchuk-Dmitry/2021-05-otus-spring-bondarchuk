package ru.otus.lec23.service.author;

import ru.otus.lec23.domain.Author;

import java.util.List;

public interface AuthorService {

    Author findAuthorById(Long id);

    List<Author> getAll();

}
