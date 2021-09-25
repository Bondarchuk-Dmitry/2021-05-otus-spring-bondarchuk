package ru.otus.lec13.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.lec13.domain.Genre;
import ru.otus.lec13.repositorie.genre.GenreRepository;
import ru.otus.lec13.util.MockEntityUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class GenreRepositoryTest {
    public final static long GENRE_ID = 2L;

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
                .matches(g -> g.getId() == mockGenre.getId())
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
                .anyMatch(genre -> genre.getId() == mockGenre.getId())
                .anyMatch(genre -> genre.getName().equals(mockGenre.getName()));
    }
}
