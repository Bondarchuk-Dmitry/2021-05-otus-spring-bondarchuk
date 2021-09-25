package ru.otus.lec13.service.book;

import ru.otus.lec13.domain.Book;

import java.util.List;

public interface BookService {

    Book findBookById(Long id);

    List<Book> getAll();

    Book save(String name, Long authorId, Long genreId);

    void update(Long id, String name, Long authorId, Long genreId);

    void delete(Long id);

}
