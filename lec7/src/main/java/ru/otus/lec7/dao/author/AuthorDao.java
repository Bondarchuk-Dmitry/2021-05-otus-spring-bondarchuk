package ru.otus.lec7.dao.author;

import ru.otus.lec7.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Optional<Author> findAuthorById(Long id);

    List<Author> getAll();

}
