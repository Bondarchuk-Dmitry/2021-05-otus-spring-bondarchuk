package ru.otus.lec7.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.lec7.domain.dto.BookDto;
import ru.otus.lec7.exception.BookNotFoundException;
import ru.otus.lec7.service.book.BookServiceImpl;
import ru.otus.lec7.util.MockEntityUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {

    public final static long BOOK_ID = 1L;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    @DisplayName("Поиск книги по id")
    @Order(0)
    public void findBookById() {
        BookDto book = bookService.findBookById(BOOK_ID);
        assertThat(book)
                .usingRecursiveComparison()
                .isEqualTo(MockEntityUtil.getBookDto(MockEntityUtil.getBook()));
    }

    @Test
    @Order(0)
    @DisplayName("Ошибка BookNotFoundException, если запись не найдена в БД по указанному id")
    public void itShouldThrowBookNotFoundExceptionWhenRecordWasNotFound() {
        assertThatCode(() -> bookService.findBookById(0L))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    @Order(0)
    @DisplayName("Все книги")
    public void getAll() {
        assertThat(bookService.getAll())
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(MockEntityUtil.getBookDto(MockEntityUtil.getBook()));
    }

    @Test
    @Order(1)
    @DisplayName("Добавление новой книги")
    public void whenInsertNewBook_thenResultInsertingSuccessful() {
        BookDto newBook = MockEntityUtil.getBookDto(MockEntityUtil.getNewBook());
        Long id = bookService.insert(newBook.getName(), newBook.getAuthor().getId(), newBook.getGenre().getId());
        assertThat(id).isEqualTo(2L);
        assertThat(bookService.findBookById(id))
                .usingRecursiveComparison()
                .isEqualTo(newBook);
    }

    @Test
    @Order(1)
    @DisplayName("Редактирование информации о книге")
    public void whenUpdateBook_thenResultUpdatingSuccessful() {
        BookDto oldBook = MockEntityUtil.getBookDto(MockEntityUtil.getBook());
        BookDto newBook = MockEntityUtil.getBookDto(MockEntityUtil.getNewBook());
        bookService.update(oldBook.getId(), newBook.getName(), newBook.getAuthor().getId(), newBook.getGenre().getId());
        newBook.setId(oldBook.getId());
        assertThat(bookService.findBookById(BOOK_ID))
                .usingRecursiveComparison()
                .isEqualTo(newBook);
    }

    @Test
    @DisplayName("Удаление книги")
    @Order(2)
    public void deleteBookTest() {
        assertThatCode(() -> bookService.findBookById(BOOK_ID))
                .doesNotThrowAnyException();
        bookService.deleteBookById(BOOK_ID);
        assertThatCode(() -> bookService.findBookById(BOOK_ID))
                .isInstanceOf(BookNotFoundException.class);
    }

}
