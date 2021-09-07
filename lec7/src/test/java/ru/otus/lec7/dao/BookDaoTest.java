package ru.otus.lec7.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.lec7.dao.book.BookDaoImpl;
import ru.otus.lec7.domain.Book;
import ru.otus.lec7.util.MockEntityUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDaoTest {

    public final static long BOOK_ID = 1L;

    @Autowired
    private BookDaoImpl bookDao;

    @Test
    @DisplayName("Поиск книги по ID")
    @Order(0)
    public void findBookByIdTest() {
        Book book = bookDao.findBookById(BOOK_ID)
                .orElse(Book.builder().build());
        assertThat(book)
                .usingRecursiveComparison()
                .isEqualTo(MockEntityUtil.getBook());
    }

    @Test
    @DisplayName("Максимальный ID")
    @Order(0)
    public void lastIdTest() {
        assertThat(bookDao.lastId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Все книги")
    @Order(0)
    public void getAllTest() {
        assertThat(bookDao.getAll())
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(MockEntityUtil.getBook());
    }

    @Test
    @DisplayName("Добавление новой книги")
    @Order(1)
    public void insertBookTest() {
        Book newBook = MockEntityUtil.getNewBook();
        bookDao.insert(newBook);
        Optional<Book> book = bookDao.findBookById(newBook.getId());
        assertThat(book.orElse(Book.builder().build()))
                .usingRecursiveComparison()
                .isEqualTo(newBook);
    }

    @Test
    @DisplayName("Редактирование информации о книге")
    @Order(2)
    public void updateBookTest() {
        Book book = MockEntityUtil.getNewBook();
        Book oldBook = bookDao.findBookById(BOOK_ID)
                .orElse(Book.builder().build());
        book.setId(oldBook.getId());
        bookDao.update(book);
        Book newBook = bookDao.findBookById(oldBook.getId()).orElse(Book.builder().build());
        assertThat(newBook)
                .usingRecursiveComparison()
                .isNotEqualTo(oldBook);
    }

    @Test
    @DisplayName("Удаление книги")
    @Order(3)
    public void deleteBookTest() {
        assertThat(bookDao.findBookById(BOOK_ID).isPresent()).isEqualTo(true);
        bookDao.deleteById(BOOK_ID);
        assertThat(bookDao.findBookById(BOOK_ID).isPresent()).isEqualTo(false);
    }

}
