package ru.otus.lec20.repositorie.genre;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.lec20.domain.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre, String> {
}
