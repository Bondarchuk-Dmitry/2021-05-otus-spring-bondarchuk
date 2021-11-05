package ru.otus.lec20.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lec20.domain.Genre;
import ru.otus.lec20.exception.GenreNotFoundException;
import ru.otus.lec20.repositorie.genre.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Flux<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Mono<Genre> findGenreById(String id) {
        return genreRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new GenreNotFoundException(String.format("Жанр по id %s не найден", id))));
    }

}
