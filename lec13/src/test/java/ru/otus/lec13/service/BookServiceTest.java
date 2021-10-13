package ru.otus.lec13.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.exception.BookNotFoundException;
import ru.otus.lec13.repositorie.book.BookRepository;
import ru.otus.lec13.service.book.BookServiceImpl;
import ru.otus.lec13.util.MockEntityUtil;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    public final static String BOOK_ID = "615fffca547bf907bda74ed5";

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private BookRepository bookRepository;

    @BeforeEach
    public void init() {
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(MockEntityUtil.getBook()));
    }

    @Test
    @DisplayName("Поиск книги по id")
    public void findBookById() {
        Book book = bookService.findBookById(BOOK_ID);
        assertThat(book)
                .usingRecursiveComparison()
                .isEqualTo(MockEntityUtil.getBook());
    }

    @Test
    @DisplayName("Ошибка BookNotFoundException, если запись не найдена в БД по указанному id")
    public void itShouldThrowBookNotFoundExceptionWhenRecordWasNotFound() {
        assertThatCode(() -> bookService.findBookById(""))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    @DisplayName("Все книги")
    public void getAll() {
        when(bookRepository.findAll()).thenReturn(List.of(MockEntityUtil.getBook()));
        assertThat(bookService.getAll())
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(MockEntityUtil.getBook());
    }

}
