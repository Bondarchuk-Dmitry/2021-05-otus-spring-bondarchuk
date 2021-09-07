package ru.otus.lec7.service.book;

import ru.otus.lec7.domain.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto findBookById(Long id);

    List<BookDto> getAll();

    Long insert(String name, Long authorId, Long genreId);

    void update(Long id, String name, Long authorId, Long genreId);

    void deleteBookById(Long id);

}
