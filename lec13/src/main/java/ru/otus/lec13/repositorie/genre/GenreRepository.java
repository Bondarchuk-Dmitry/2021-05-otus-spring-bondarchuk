package ru.otus.lec13.repositorie.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.lec13.domain.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, Long> {

}
