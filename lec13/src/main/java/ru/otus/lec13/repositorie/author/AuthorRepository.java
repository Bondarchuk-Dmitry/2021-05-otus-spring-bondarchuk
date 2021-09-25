package ru.otus.lec13.repositorie.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.lec13.domain.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, Long> {

}
