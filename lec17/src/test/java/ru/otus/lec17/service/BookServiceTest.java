package ru.otus.lec17.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lec17.domain.Book;
import ru.otus.lec17.exception.BookNotFoundException;
import ru.otus.lec17.repositorie.book.BookRepository;
import ru.otus.lec17.service.author.AuthorService;
import ru.otus.lec17.service.book.BookServiceImpl;
import ru.otus.lec17.service.genre.GenreService;
import ru.otus.lec17.util.MockEntityUtil;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    public final static long BOOK_ID = 1L;

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

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
        assertThatCode(() -> bookService.findBookById(0L))
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

    @Test
    @DisplayName("Добавление новой книги")
    public void whenInsertNewBook_thenResultInsertingSuccessful() {

        when(authorService.findAuthorById(1L)).thenReturn(MockEntityUtil.getAuthorPushkin());
        when(genreService.findGenreById(1L)).thenReturn(MockEntityUtil.getGenrePoetry());
        when(bookRepository.save(MockEntityUtil.getInsertBook())).thenReturn(MockEntityUtil.getNewBook());
        when(bookRepository.findById(2L)).thenReturn(Optional.of(MockEntityUtil.getNewBook()));

        Book newBook = MockEntityUtil.getNewBook();
        Book book = bookService.save(newBook.getName(), newBook.getAuthor().getId(), newBook.getGenre().getId());
        assertThat(book).isEqualTo(MockEntityUtil.getNewBook());
        assertThat(bookService.findBookById(2L))
                .usingRecursiveComparison()
                .isEqualTo(newBook);
    }

    @Test
    @DisplayName("Редактирование информации о книге")
    public void whenUpdateBook_thenResultUpdatingSuccessful() {
        Book oldBook = MockEntityUtil.getBook();
        Book newBook = MockEntityUtil.getNewBook();
        newBook.setId(oldBook.getId());

        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(newBook));
        when(authorService.findAuthorById(1L)).thenReturn(newBook.getAuthor());
        when(genreService.findGenreById(1L)).thenReturn(newBook.getGenre());

        bookService.update(oldBook.getId(), newBook.getName(), newBook.getAuthor().getId(), newBook.getGenre().getId());
        assertThat(bookService.findBookById(BOOK_ID))
                .usingRecursiveComparison()
                .isEqualTo(newBook);
    }

    @Test
    @DisplayName("Удаление книги")
    public void deleteBookTest() {
        assertThatCode(() -> bookService.findBookById(BOOK_ID))
                .doesNotThrowAnyException();

        bookService.delete(BOOK_ID);

        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.empty());

        assertThatCode(() -> bookService.findBookById(BOOK_ID))
                .isInstanceOf(BookNotFoundException.class);
    }

}
