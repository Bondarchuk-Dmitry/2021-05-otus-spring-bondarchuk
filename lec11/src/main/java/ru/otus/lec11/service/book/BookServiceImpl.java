package ru.otus.lec11.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lec11.domain.Author;
import ru.otus.lec11.domain.Book;
import ru.otus.lec11.domain.Genre;
import ru.otus.lec11.exception.BookNotFoundException;
import ru.otus.lec11.repositorie.book.BookRepository;
import ru.otus.lec11.service.author.AuthorService;
import ru.otus.lec11.service.genre.GenreService;

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
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
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
        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        Book newBook = prepareBook(oldBook.getId(), name, author, genre);
        bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        bookRepository.delete(book);
    }

    private Book prepareBook(Long id, String name, Author author, Genre genre) {
        return new Book(id, name, author, genre, List.of());
    }

}
