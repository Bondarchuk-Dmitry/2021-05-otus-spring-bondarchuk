package ru.otus.lec7.util;

import ru.otus.lec7.domain.Author;
import ru.otus.lec7.domain.Book;
import ru.otus.lec7.domain.Genre;

public class MockEntityUtil {

    public static Author getAuthor() {
        return Author.builder()
                .id(2L)
                .firstName("Кей")
                .lastName("Хорстман")
                .build();
    }

    public static Author getAuthorPushkin() {
        return Author.builder()
                .id(1L)
                .firstName("Александр")
                .lastName("Пушкин")
                .build();
    }

    public static Genre getGenre() {
        return Genre.builder()
                .id(2L)
                .name("Нехудожественная литература")
                .build();
    }

    public static Genre getGenrePoetry() {
        return Genre.builder()
                .id(1L)
                .name("Поэзия")
                .build();
    }

    public static Book getBook() {
        return Book.builder()
                .id(1L)
                .name("Java. Библиотека профессионала. Том 1. Основы")
                .author(getAuthor())
                .genre(getGenre())
                .build();
    }

    public static Book getNewBook() {
        return Book.builder()
                .id(2L)
                .name("test")
                .author(getAuthorPushkin())
                .genre(getGenrePoetry())
                .build();
    }

    public static Book getInsertBook() {
        return Book.builder()
                .id(0L)
                .name("test")
                .author(getAuthorPushkin())
                .genre(getGenrePoetry())
                .build();
    }


}
