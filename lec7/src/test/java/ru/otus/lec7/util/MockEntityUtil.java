package ru.otus.lec7.util;

import ru.otus.lec7.domain.Author;
import ru.otus.lec7.domain.Book;
import ru.otus.lec7.domain.Genre;
import ru.otus.lec7.domain.dto.BookDto;

public class MockEntityUtil {

    public static Author getAuthor() {
        return Author.builder()
                .id(2L)
                .firstName("Кей")
                .lastName("Хорстман")
                .build();
    }

    public static Genre getGenre() {
        return Genre.builder()
                .id(2L)
                .name("Нехудожественная литература")
                .build();
    }

    public static Book getBook() {
        return Book.builder()
                .id(1L)
                .name("Java. Библиотека профессионала. Том 1. Основы")
                .authorId(2L)
                .genreId(2L)
                .build();
    }

    public static Book getNewBook() {
        return Book.builder()
                .id(2L)
                .name("test")
                .authorId(1L)
                .genreId(1L)
                .build();
    }

    public static BookDto getBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(getAuthor())
                .genre(getGenre())
                .build();
    }


}
