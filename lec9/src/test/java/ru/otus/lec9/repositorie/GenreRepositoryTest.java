package ru.otus.lec9.repositorie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.lec9.domain.Genre;
import ru.otus.lec9.repositorie.genre.GenreRepository;
import ru.otus.lec9.repositorie.genre.GenreRepositoryJpa;
import ru.otus.lec9.util.MockEntityUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
public class GenreRepositoryTest {
    public final static long GENRE_ID = 2L;

    @Autowired
    private GenreRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Поиск жанра по ID")
    public void findGenreByIdTest() {
        Genre genre = repository.findGenreById(GENRE_ID)
                .orElse(new Genre());
        Genre mockGenre= MockEntityUtil.getGenre();
        assertThat(genre)
                .isNotNull()
                .matches(g -> g.getId() == mockGenre.getId())
                .matches(g -> g.getName().equals(mockGenre.getName()));
    }

    @Test
    @DisplayName("Все Жанры")
    public void getAllTest() {
        List<Genre> genres = repository.getAll();
        Genre mockGenre = MockEntityUtil.getGenre();

        assertThat(genres)
                .isNotNull()
                .hasSize(2)
                .anyMatch(genre -> genre.getId() == mockGenre.getId())
                .anyMatch(genre -> genre.getName().equals(mockGenre.getName()));
    }
}
