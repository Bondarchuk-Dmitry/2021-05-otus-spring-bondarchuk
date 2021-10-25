package ru.otus.lec17.repositorie.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec17.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
