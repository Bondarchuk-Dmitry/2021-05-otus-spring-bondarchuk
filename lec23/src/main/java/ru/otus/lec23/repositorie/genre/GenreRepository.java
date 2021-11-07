package ru.otus.lec23.repositorie.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec23.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
