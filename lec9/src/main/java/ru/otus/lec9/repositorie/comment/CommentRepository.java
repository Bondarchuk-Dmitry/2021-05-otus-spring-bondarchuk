package ru.otus.lec9.repositorie.comment;

import ru.otus.lec9.domain.CommentBook;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Optional<CommentBook> findCommentById(long id);

    List<CommentBook> getAll();

    CommentBook save(CommentBook comment);

    void update(CommentBook comment);

    void delete(CommentBook comment);

}
