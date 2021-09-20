package ru.otus.lec9.repositorie.genre;

import ru.otus.lec9.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Optional<Genre> findGenreById(Long id);

    List<Genre> getAll();
}
