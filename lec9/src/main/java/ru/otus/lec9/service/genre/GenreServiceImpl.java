package ru.otus.lec9.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lec9.repositorie.genre.GenreRepository;
import ru.otus.lec9.domain.Genre;
import ru.otus.lec9.exception.GenreNotFoundException;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Genre findGenreById(Long id) {
        return genreRepository.findGenreById(id)
                .orElseThrow(() -> new GenreNotFoundException(String.format("Жанр по id %d не найден", id)));
    }

}
