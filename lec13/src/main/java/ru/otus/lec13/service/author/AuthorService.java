package ru.otus.lec13.service.author;

import ru.otus.lec13.domain.Author;

import java.util.List;

public interface AuthorService {

    Author findAuthorById(String id);

    List<Author> getAll();

}
