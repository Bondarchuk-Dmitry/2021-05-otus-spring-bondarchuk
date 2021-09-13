package ru.otus.lec7.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec7.dao.genre.GenreDao;
import ru.otus.lec7.domain.Genre;
import ru.otus.lec7.exception.GenreNotFoundException;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Genre findGenreById(Long id) {
        return genreDao.findGenreById(id)
                .orElseThrow(() -> new GenreNotFoundException(String.format("Жанр по id %d не найден", id)));
    }

}
