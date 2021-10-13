package ru.otus.lec16.service.author;

import ru.otus.lec16.domain.Author;

import java.util.List;

public interface AuthorService {

    Author findAuthorById(Long id);

    List<Author> getAll();

}
