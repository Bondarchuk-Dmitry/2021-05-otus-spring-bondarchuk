package ru.otus.lec16.repositorie.book;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec16.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "otus-book-author-genre-entity-graph")
    List<Book> findAll();

}
