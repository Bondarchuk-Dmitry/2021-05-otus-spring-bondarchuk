package ru.otus.lec13.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec13.domain.Genre;
import ru.otus.lec13.exception.GenreNotFoundException;
import ru.otus.lec13.repositorie.genre.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Genre findGenreById(String id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(String.format("Жанр по id %s не найден", id)));
    }

}
