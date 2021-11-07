package ru.otus.lec23.service.comment;

import ru.otus.lec23.domain.CommentBook;

import java.util.List;

public interface CommentService {

    CommentBook findCommentById(long id);

    List<CommentBook> getAll();

    List<CommentBook> findCommentByBookId(long id);

    CommentBook save(String text, long bookId);

    void update(long id, String text, long bookId);

    void delete(long id);
}
