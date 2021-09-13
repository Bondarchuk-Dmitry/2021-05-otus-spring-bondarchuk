package ru.otus.lec7.service.author;

import ru.otus.lec7.domain.Author;

import java.util.List;

public interface AuthorService {

    Author findAuthorById(Long id);

    List<Author> getAll();

}
