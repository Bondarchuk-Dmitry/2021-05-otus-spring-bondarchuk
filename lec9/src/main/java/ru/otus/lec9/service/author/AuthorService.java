package ru.otus.lec9.service.author;

import ru.otus.lec9.domain.Author;

import java.util.List;

public interface AuthorService {

    Author findAuthorById(Long id);

    List<Author> getAll();

}
