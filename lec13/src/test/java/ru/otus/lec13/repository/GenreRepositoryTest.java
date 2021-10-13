package ru.otus.lec13.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.lec13.domain.Genre;
import ru.otus.lec13.repositorie.genre.GenreRepository;
import ru.otus.lec13.util.MockEntityUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
public class GenreRepositoryTest {
    public final static String GENRE_ID = "61601c5e6592350e22a07fbb";

    @Autowired
    private GenreRepository repository;


    @Test
    @DisplayName("Поиск жанра по ID")
    public void findGenreByIdTest() {
        Genre genre = repository.findById(GENRE_ID)
                .orElse(new Genre());
        Genre mockGenre = MockEntityUtil.getGenre();
        assertThat(genre)
                .isNotNull()
                .matches(g -> g.getId().equals(mockGenre.getId()))
                .matches(g -> g.getName().equals(mockGenre.getName()));
    }

    @Test
    @DisplayName("Все Жанры")
    public void getAllTest() {
        List<Genre> genres = repository.findAll();
        Genre mockGenre = MockEntityUtil.getGenre();

        assertThat(genres)
                .isNotNull()
                .hasSize(2)
                .anyMatch(genre -> genre.getId().equals(mockGenre.getId()))
                .anyMatch(genre -> genre.getName().equals(mockGenre.getName()));
    }
}
