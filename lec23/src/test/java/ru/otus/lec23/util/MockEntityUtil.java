package ru.otus.lec23.util;

import ru.otus.lec23.domain.Author;
import ru.otus.lec23.domain.Book;
import ru.otus.lec23.domain.CommentBook;
import ru.otus.lec23.domain.Genre;

import java.util.List;

public class MockEntityUtil {

    public static Author getAuthor() {
        return new Author(2L, "Кей", "Хорстман");
    }

    public static Author getAuthorPushkin() {
        return new Author(1L, "Александр", "Пушкин");
    }

    public static Genre getGenre() {
        return new Genre(2L, "Нехудожественная литература");
    }

    public static Genre getGenrePoetry() {
        return new Genre(1L, "Поэзия");
    }

    public static Book getBook() {
        return new Book(1L, "Java. Библиотека профессионала. Том 1. Основы", getAuthor(), getGenre(), List.of());
    }

    public static Book getNewBook() {
        return new Book(2L, "test", getAuthorPushkin(), getGenrePoetry(), List.of());
    }

    public static Book getInsertBook() {
        return new Book(0L, "test", getAuthorPushkin(), getGenrePoetry(), List.of());
    }

    public static CommentBook getCommentBook() {
        return new CommentBook(1L, "test", getBook());
    }
}
