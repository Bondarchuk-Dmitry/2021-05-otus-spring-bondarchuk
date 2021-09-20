package ru.otus.lec9.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lec9.repositorie.book.BookRepository;
import ru.otus.lec9.domain.Author;
import ru.otus.lec9.domain.Book;
import ru.otus.lec9.domain.Genre;
import ru.otus.lec9.exception.BookNotFoundException;
import ru.otus.lec9.service.author.AuthorService;
import ru.otus.lec9.service.genre.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional(readOnly = true)
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    @Transactional
    public Book save(String name, Long authorId, Long genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book book = prepareBook(0L, name, author, genre);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void update(Long id, String name, Long authorId, Long genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book oldBook = bookRepository.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        Book newBook = prepareBook(oldBook.getId(), name, author, genre);
        bookRepository.update(newBook);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book book = bookRepository.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        bookRepository.delete(book);
    }

    private Book prepareBook(Long id, String name, Author author, Genre genre) {
        return new Book(id, name, author, genre, List.of());
    }

}
