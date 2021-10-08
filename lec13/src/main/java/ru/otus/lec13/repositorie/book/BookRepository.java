package ru.otus.lec13.repositorie.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lec13.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
