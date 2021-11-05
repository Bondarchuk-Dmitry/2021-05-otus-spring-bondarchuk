package ru.otus.lec20.service.genre;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lec20.domain.Genre;

public interface GenreService {

    Flux<Genre> findAll();
    Mono<Genre> findGenreById(String id);

}
