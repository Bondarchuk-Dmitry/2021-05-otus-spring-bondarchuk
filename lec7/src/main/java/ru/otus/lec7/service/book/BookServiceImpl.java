package ru.otus.lec7.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec7.dao.book.BookDao;
import ru.otus.lec7.domain.Author;
import ru.otus.lec7.domain.Book;
import ru.otus.lec7.domain.Genre;
import ru.otus.lec7.exception.BookNotFoundException;
import ru.otus.lec7.service.author.AuthorService;
import ru.otus.lec7.service.genre.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Book findBookById(Long id) {
        return bookDao.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Long insert(String name, Long authorId, Long genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book book = prepareBook(0L, name, author, genre);
        return bookDao.insert(book);
    }

    @Override
    public void update(Long id, String name, Long authorId, Long genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book oldBook = bookDao.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        Book newBook = prepareBook(oldBook.getId(), name, author, genre);
        bookDao.update(newBook);
    }

    @Override
    public void deleteBookById(Long id) {
        Book book = bookDao.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        bookDao.deleteById(book.getId());
    }

    private Book prepareBook(Long id, String name, Author author, Genre genre) {
        return Book.builder()
                .id(id)
                .name(name)
                .author(author)
                .genre(genre)
                .build();
    }

}
