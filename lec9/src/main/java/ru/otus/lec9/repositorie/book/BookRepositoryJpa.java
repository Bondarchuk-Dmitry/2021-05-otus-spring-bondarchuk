package ru.otus.lec9.repositorie.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.lec9.domain.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    private final EntityManager em;

    @Override
    public Optional<Book> findBookById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select a from Book a", Book.class)
                .getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }

    @Override
    public void delete(Book book) {
        em.remove(book);
    }
}
