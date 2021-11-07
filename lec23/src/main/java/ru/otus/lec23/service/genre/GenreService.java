package ru.otus.lec23.service.genre;

import ru.otus.lec23.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre findGenreById(Long id);

    List<Genre> getAll();

}
