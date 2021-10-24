package ru.otus.lec17.service.genre;

import ru.otus.lec17.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre findGenreById(Long id);

    List<Genre> getAll();

}
