package ru.otus.lec16.service.comment;

import ru.otus.lec16.domain.CommentBook;

import java.util.List;

public interface CommentService {

    CommentBook findCommentById(long id);

    List<CommentBook> getAll();

    CommentBook save(String text, long bookId);

    void update(long id, String text, long bookId);

    void delete(long id);
}
