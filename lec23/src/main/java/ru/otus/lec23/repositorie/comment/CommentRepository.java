package ru.otus.lec23.repositorie.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec23.domain.CommentBook;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentBook, Long> {

    List<CommentBook> findAllByBookId(Long bookId);
}
