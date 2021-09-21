package ru.otus.lec11.repositorie;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import ru.otus.lec11.domain.Book;
import ru.otus.lec11.domain.CommentBook;
import ru.otus.lec11.repositorie.book.BookRepository;
import ru.otus.lec11.util.MockEntityUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    public final static long BOOK_ID = 1L;

    private static final int EXPECTED_QUERIES_COUNT = 2;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Поиск книги по ID")
    public void findBookByIdTest() {
        Book book = bookRepository.getById(BOOK_ID);
        Book mockBook = MockEntityUtil.getBook();
        CommentBook comment = MockEntityUtil.getCommentBook();

        assertThat(book)
                .isNotNull()
                .matches(b -> b.getName().equals(mockBook.getName()))
                .matches(b -> b.getAuthor().equals(mockBook.getAuthor()))
                .matches(b -> b.getGenre().equals(mockBook.getGenre()))
                .matches(b -> b.getBookComments().size() > 0);

        assertThat(book.getBookComments())
                .isNotNull()
                .hasSize(2)
                .anyMatch(commentBook -> commentBook.getText().equals(comment.getText()))
                .anyMatch(commentBook -> commentBook.getId() == comment.getId());
    }

    @Test
    @DisplayName("Все книги. Исключает N+1")
    public void getAllTest() {
        SessionFactory sessionFactory = em.getEntityManager()
                .getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory
                .getStatistics()
                .setStatisticsEnabled(true);

        List<Book> books = bookRepository.findAll();
        Book mockBook = MockEntityUtil.getBook();

        assertThat(books)
                .isNotNull()
                .hasSize(1)
                .anyMatch(book -> book.getName().equals(mockBook.getName()))
                .anyMatch(book -> book.getAuthor().equals(mockBook.getAuthor()))
                .anyMatch(book -> book.getGenre().equals(mockBook.getGenre()))
                .anyMatch(book -> book.getBookComments().size() > 0);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @Test
    @DisplayName("Добавление новой книги")
    @Rollback
    public void insertBookTest() {
        Book newBook = MockEntityUtil.getNewBook();
        Book mockNewBook = MockEntityUtil.getNewBook();
        bookRepository.save(newBook);
        Book book = bookRepository.getById(newBook.getId());

        assertThat(book)
                .isNotNull()
                .matches(b -> b.getName().equals(mockNewBook.getName()))
                .matches(b -> b.getAuthor().equals(mockNewBook.getAuthor()))
                .matches(b -> b.getGenre().equals(mockNewBook.getGenre()))
                .matches(b -> b.getBookComments().isEmpty());
    }

    @Test
    @DisplayName("Редактирование информации о книге")
    @Rollback
    public void updateBookTest() {
        Book mockNewBook = MockEntityUtil.getNewBook();
        Book oldBook = bookRepository.getById(BOOK_ID);
        mockNewBook.setId(oldBook.getId());
        mockNewBook.setBookComments(oldBook.getBookComments());
        bookRepository.save(mockNewBook);
        Book newBook = bookRepository.getById(oldBook.getId());
        CommentBook comment = MockEntityUtil.getCommentBook();

        assertThat(newBook)
                .isNotNull()
                .matches(b -> b.getName().equals(mockNewBook.getName()))
                .matches(b -> b.getAuthor().equals(mockNewBook.getAuthor()))
                .matches(b -> b.getGenre().equals(mockNewBook.getGenre()))
                .matches(b -> b.getBookComments().size() > 0);

        assertThat(newBook.getBookComments())
                .isNotNull()
                .hasSize(2)
                .anyMatch(commentBook -> commentBook.getText().equals(comment.getText()))
                .anyMatch(commentBook -> commentBook.getId() == comment.getId());
    }

    @Test
    @DisplayName("Удаление книги")
    @Rollback
    public void deleteBookTest() {
        Book book = bookRepository.getById(BOOK_ID);
        assertThat(bookRepository.findById(BOOK_ID).isPresent()).isEqualTo(true);
        bookRepository.delete(book);
        assertThat(bookRepository.findById(BOOK_ID).isPresent()).isEqualTo(false);
    }

}
