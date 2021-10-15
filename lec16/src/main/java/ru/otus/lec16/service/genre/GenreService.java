package ru.otus.lec16.service.genre;

import ru.otus.lec16.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre findGenreById(Long id);

    List<Genre> getAll();

}
