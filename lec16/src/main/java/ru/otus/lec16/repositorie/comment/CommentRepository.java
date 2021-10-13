package ru.otus.lec16.repositorie.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec16.domain.CommentBook;

public interface CommentRepository extends JpaRepository<CommentBook, Long> {

}
