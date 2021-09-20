package ru.otus.lec11.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lec11.domain.Genre;
import ru.otus.lec11.exception.GenreNotFoundException;
import ru.otus.lec11.repositorie.genre.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Genre findGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(String.format("Жанр по id %d не найден", id)));
    }

}
