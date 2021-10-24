package ru.otus.lec17.repositorie.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec17.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
