package ru.otus.lec13.repositorie.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lec13.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
