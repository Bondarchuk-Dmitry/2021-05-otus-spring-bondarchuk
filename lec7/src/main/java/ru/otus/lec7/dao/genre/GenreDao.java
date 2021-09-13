package ru.otus.lec7.dao.genre;

import ru.otus.lec7.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Optional<Genre> findGenreById(Long id);

    List<Genre> getAll();
}
