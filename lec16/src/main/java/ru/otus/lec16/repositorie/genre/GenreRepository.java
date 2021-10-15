package ru.otus.lec16.repositorie.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec16.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
