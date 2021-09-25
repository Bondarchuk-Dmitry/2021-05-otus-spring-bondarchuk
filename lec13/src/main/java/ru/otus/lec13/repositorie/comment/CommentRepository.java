package ru.otus.lec13.repositorie.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.lec13.domain.CommentBook;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<CommentBook, Long> {

    List<CommentBook> findCommentBookByBookId(Long id);

}
