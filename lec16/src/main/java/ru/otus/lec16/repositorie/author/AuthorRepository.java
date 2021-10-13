package ru.otus.lec16.repositorie.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec16.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
