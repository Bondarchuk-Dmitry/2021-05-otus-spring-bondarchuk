package ru.otus.lec9.repositorie.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.lec9.domain.Genre;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

    private final EntityManager em;

    @Override
    public Optional<Genre> findGenreById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("SELECT g FROM Genre g", Genre.class)
                .getResultList();
    }
}
