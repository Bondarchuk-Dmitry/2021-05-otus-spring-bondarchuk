package ru.otus.lec13.util;

import ru.otus.lec13.domain.Author;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.domain.CommentBook;
import ru.otus.lec13.domain.Genre;

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
        Book book = new Book(1L, "Java. Библиотека профессионала. Том 1. Основы", getAuthor().getId(), getGenre().getId());
        book.setGenre(getGenre());
        book.setAuthor(getAuthor());
        book.setBookComments(List.of(getCommentBookTest1(), getCommentBookTest2()));
        return book;
    }

    public static CommentBook getCommentBookTest1() {
        return new CommentBook(1L, "test1", 1L);
    }

    public static CommentBook getCommentBookTest2() {
        return new CommentBook(2L, "test2", 1L);
    }
}
