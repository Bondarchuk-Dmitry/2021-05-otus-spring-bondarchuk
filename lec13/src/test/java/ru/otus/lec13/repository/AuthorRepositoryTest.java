package ru.otus.lec13.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.lec13.domain.Author;
import ru.otus.lec13.repositorie.author.AuthorRepository;
import ru.otus.lec13.util.MockEntityUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class AuthorRepositoryTest {

    public final static long AUTHOR_ID = 2L;

    @Autowired
    private AuthorRepository repository;

    @Test
    @DisplayName("Поиск автора по ID")
    public void findAuthorByIdTest() {
        Author author = repository.findById(AUTHOR_ID)
                .orElse(new Author());
        Author mockAuthor = MockEntityUtil.getAuthor();
        assertThat(author)
                .isNotNull()
                .matches(a -> a.getId() == mockAuthor.getId())
                .matches(a -> a.getFirstName().equals(mockAuthor.getFirstName()))
                .matches(a -> a.getLastName().equals(mockAuthor.getLastName()));
    }

    @Test
    @DisplayName("Все Авторы")
    public void getAllTest() {
        List<Author> authors = repository.findAll();
        Author mockAuthor = MockEntityUtil.getAuthor();

        assertThat(authors)
                .isNotNull()
                .hasSize(2)
                .anyMatch(author -> author.getId() == mockAuthor.getId())
                .anyMatch(author -> author.getFirstName().equals(mockAuthor.getFirstName()))
                .anyMatch(author -> author.getLastName().equals(mockAuthor.getLastName()));
    }

}
