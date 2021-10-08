package ru.otus.lec13.repositorie.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lec13.domain.Author;


public interface AuthorRepository extends MongoRepository<Author, String> {
}
