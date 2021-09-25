package ru.otus.lec13.repositorie.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.lec13.domain.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

}
