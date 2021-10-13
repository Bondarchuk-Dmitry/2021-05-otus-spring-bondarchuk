package ru.otus.lec13.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec13.domain.Author;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.domain.CommentBook;
import ru.otus.lec13.domain.Genre;
import ru.otus.lec13.exception.BookNotFoundException;
import ru.otus.lec13.repositorie.book.BookRepository;
import ru.otus.lec13.service.author.AuthorService;
import ru.otus.lec13.service.genre.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Book findBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %s не найдена", id)));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(String name, String authorId, String genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book book = prepareBook(null, name, author, genre);
        return bookRepository.save(book);
    }

    @Override
    public Book addComment(String bookId, String text) {
        Book book = this.findBookById(bookId);
        book.getBookComments().add(new CommentBook(text));
        return bookRepository.save(book);
    }

    @Override
    public void update(String id, String name, String authorId, String genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %s не найдена", id)));
        Book newBook = prepareBook(oldBook.getId(), name, author, genre);
        bookRepository.save(newBook);
    }

    @Override
    public void delete(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %s не найдена", id)));
        bookRepository.delete(book);
    }

    private Book prepareBook(String id, String name, Author author, Genre genre) {
        return new Book(id, name, author, genre);
    }

}
