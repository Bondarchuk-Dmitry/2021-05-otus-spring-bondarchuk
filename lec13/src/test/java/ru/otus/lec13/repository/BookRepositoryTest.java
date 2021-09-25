package ru.otus.lec13.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.repositorie.book.BookRepository;
import ru.otus.lec13.util.MockEntityUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookRepositoryTest {

    public final static long BOOK_ID = 1L;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Поиск книги по ID")
    public void findBookByIdTest() {
        Book book = bookRepository.findById(BOOK_ID)
                .orElse(new Book());
        Book mockBook = MockEntityUtil.getBook();

        assertThat(book)
                .isNotNull()
                .matches(b -> b.getName().equals(mockBook.getName()))
                .matches(b -> b.getAuthorId().equals(mockBook.getAuthor().getId()))
                .matches(b -> b.getGenreId().equals(mockBook.getGenre().getId()));
    }

    @Test
    @DisplayName("Все книги")
    public void getAllTest() {
        List<Book> books = bookRepository.findAll();
        Book mockBook = MockEntityUtil.getBook();

        assertThat(books)
                .isNotNull()
                .hasSize(1)
                .anyMatch(book -> book.getName().equals(mockBook.getName()))
                .anyMatch(book -> book.getAuthorId().equals(mockBook.getAuthor().getId()))
                .anyMatch(book -> book.getGenreId().equals(mockBook.getGenre().getId()));
    }

}
