package ru.otus.lec7.service.book;

import ru.otus.lec7.domain.Book;

import java.util.List;

public interface BookService {

    Book findBookById(Long id);

    List<Book> getAll();

    Long insert(String name, Long authorId, Long genreId);

    void update(Long id, String name, Long authorId, Long genreId);

    void deleteBookById(Long id);

}
