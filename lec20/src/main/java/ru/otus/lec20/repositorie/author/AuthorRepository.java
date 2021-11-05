package ru.otus.lec20.repositorie.author;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.lec20.domain.Author;


public interface AuthorRepository extends ReactiveCrudRepository<Author, String> {
}
