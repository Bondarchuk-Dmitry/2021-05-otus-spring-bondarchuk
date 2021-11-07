package ru.otus.lec23.repositorie.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec23.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
