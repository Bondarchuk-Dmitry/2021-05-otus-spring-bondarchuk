package ru.otus.lec11.repositorie.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.lec11.domain.CommentBook;

@Repository
public interface CommentRepository extends JpaRepository<CommentBook, Long> {

}
