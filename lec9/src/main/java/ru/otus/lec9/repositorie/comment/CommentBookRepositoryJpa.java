package ru.otus.lec9.repositorie.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.lec9.domain.CommentBook;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentBookRepositoryJpa implements CommentRepository {

    private final EntityManager em;

    @Override
    public Optional<CommentBook> findCommentById(long id) {
        return Optional.ofNullable(em.find(CommentBook.class, id));
    }

    @Override
    public List<CommentBook> getAll() {
        return em.createQuery("select c from CommentBook c")
                .getResultList();
    }

    @Override
    public CommentBook save(CommentBook comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public void update(CommentBook comment) {
        em.merge(comment);
    }

    @Override
    public void delete(CommentBook comment) {
        em.remove(comment);
    }
}
