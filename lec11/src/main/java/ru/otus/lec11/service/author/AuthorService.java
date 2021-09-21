package ru.otus.lec11.service.author;

import ru.otus.lec11.domain.Author;

import java.util.List;

public interface AuthorService {

    Author findAuthorById(Long id);

    List<Author> getAll();

}
