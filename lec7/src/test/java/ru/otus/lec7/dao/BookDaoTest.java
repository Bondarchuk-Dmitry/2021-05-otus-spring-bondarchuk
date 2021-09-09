package ru.otus.lec7.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.lec7.dao.book.BookDaoJdbc;
import ru.otus.lec7.domain.Book;
import ru.otus.lec7.util.MockEntityUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoTest {

    public final static long BOOK_ID = 1L;

    @Autowired
    private BookDaoJdbc bookDao;

    @Test
    @DisplayName("Поиск книги по ID")
    public void findBookByIdTest() {
        Book book = bookDao.findBookById(BOOK_ID)
                .orElse(Book.builder().build());
        assertThat(book)
                .usingRecursiveComparison()
                .isEqualTo(MockEntityUtil.getBook());
    }

    @Test
    @DisplayName("Все книги")
    public void getAllTest() {
        assertThat(bookDao.getAll())
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(MockEntityUtil.getBook());
    }

    @Test
    @DisplayName("Добавление новой книги")
    @Rollback
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
    @Rollback
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
    @Rollback
    public void deleteBookTest() {
        assertThat(bookDao.findBookById(BOOK_ID).isPresent()).isEqualTo(true);
        bookDao.deleteById(BOOK_ID);
        assertThat(bookDao.findBookById(BOOK_ID).isPresent()).isEqualTo(false);
    }

}
