package ru.otus.lec9.repositorie.author;

import ru.otus.lec9.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Optional<Author> findAuthorById(Long id);

    List<Author> getAll();

}
