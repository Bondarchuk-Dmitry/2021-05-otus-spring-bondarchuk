package ru.otus.lec9.service.comment;

import ru.otus.lec9.domain.CommentBook;

import java.util.List;

public interface CommentService {

    CommentBook findCommentById(long id);

    List<CommentBook> getAll();

    CommentBook save(String text, long bookId);

    void update(long id, String text, long bookId);

    void delete(long id);
}
