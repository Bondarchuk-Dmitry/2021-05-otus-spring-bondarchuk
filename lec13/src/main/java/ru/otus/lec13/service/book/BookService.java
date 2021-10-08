package ru.otus.lec13.service.book;

import ru.otus.lec13.domain.Book;

import java.util.List;

public interface BookService {

    Book findBookById(String id);

    List<Book> getAll();

    Book save(String name, String authorId, String genreId);

    Book addComment(String bookId, String text);

    void update(String id, String name, String authorId, String genreId);

    void delete(String id);

}
