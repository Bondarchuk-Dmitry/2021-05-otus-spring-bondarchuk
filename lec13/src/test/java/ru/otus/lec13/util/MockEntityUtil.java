package ru.otus.lec13.util;

import ru.otus.lec13.domain.Author;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.domain.CommentBook;
import ru.otus.lec13.domain.Genre;

import java.util.List;

public class MockEntityUtil {

    public static Author getAuthor() {
        return new Author("61601c1540cb0c51c1187048", "Кей", "Хорстман");
    }

    public static Author getAuthorPushkin() {
        return new Author("61601c4040cb0c51c118704a", "Александр", "Пушкин");
    }

    public static Genre getGenre() {
        return new Genre("61601c5e6592350e22a07fbb", "Нехудожественная литература");
    }

    public static Genre getGenrePoetry() {
        return new Genre("61601c6f6592350e22a07fbd", "Поэзия");
    }

    public static Book getBook() {
        Book book = new Book("615fffca547bf907bda74ed5", "Java. Библиотека профессионала. Том 1. Основы", getAuthor(), getGenre());
        book.setGenre(getGenre());
        book.setAuthor(getAuthor());
        book.setBookComments(List.of(getCommentBookTest1(), getCommentBookTest2()));
        return book;
    }

    public static CommentBook getCommentBookTest1() {
        return new CommentBook("test1");
    }

    public static CommentBook getCommentBookTest2() {
        return new CommentBook("test2");
    }
}
