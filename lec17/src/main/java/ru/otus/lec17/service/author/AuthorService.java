package ru.otus.lec17.service.author;

import ru.otus.lec17.domain.Author;

import java.util.List;

public interface AuthorService {

    Author findAuthorById(Long id);

    List<Author> getAll();

}
