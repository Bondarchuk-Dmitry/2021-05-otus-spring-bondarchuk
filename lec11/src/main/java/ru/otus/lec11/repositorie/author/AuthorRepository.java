package ru.otus.lec11.repositorie.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.lec11.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
