package ru.otus.lec7.dao.book;

import ru.otus.lec7.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Long lastId();

    Optional<Book> findBookById(long id);

    List<Book> getAll();

    void insert(Book book);

    void update(Book book);

    void deleteById(Long id);

}
