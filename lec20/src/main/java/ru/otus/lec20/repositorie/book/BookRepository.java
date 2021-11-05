package ru.otus.lec20.repositorie.book;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.lec20.domain.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, String> {

}
