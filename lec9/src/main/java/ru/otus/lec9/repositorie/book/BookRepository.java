package ru.otus.lec9.repositorie.book;

import ru.otus.lec9.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findBookById(long id);

    List<Book> getAll();

    Book save(Book book);

    void update(Book book);

    void delete(Book book);

}
