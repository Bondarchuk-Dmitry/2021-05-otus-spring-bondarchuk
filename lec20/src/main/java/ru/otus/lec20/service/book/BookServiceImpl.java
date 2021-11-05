package ru.otus.lec20.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lec20.domain.Author;
import ru.otus.lec20.domain.Book;
import ru.otus.lec20.domain.CommentBook;
import ru.otus.lec20.domain.Genre;
import ru.otus.lec20.exception.BookNotFoundException;
import ru.otus.lec20.repositorie.book.BookRepository;
import ru.otus.lec20.service.author.AuthorService;
import ru.otus.lec20.service.genre.GenreService;

import java.util.ArrayList;
import java.util.Collections;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Mono<Book> findBookById(String id) {
        return bookRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new BookNotFoundException(String.format("Книга по id %s не найдена", id))));
    }

    @Override
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> save(String name, String authorId, String genreId) {
        return Mono.zip(
                authorService.findAuthorById(authorId),
                genreService.findGenreById(genreId))
                .map(data -> prepareBook(null, name, data.getT1(), data.getT2()))
                .flatMap(bookRepository::save);
    }

    @Override
    public Mono<Book> addComment(String bookId, String text) {
        return this.findBookById(bookId)
                .flatMap(book -> {
                    book.getBookComments().add(new CommentBook(text));
                    return bookRepository.save(book);
                });
    }

    @Override
    public Mono<Book> update(String id, String name, String authorId, String genreId) {
        return Mono.zip(
                authorService.findAuthorById(authorId),
                genreService.findGenreById(genreId),
                this.findBookById(id))
                .map(data -> prepareBook(data.getT3(), name, data.getT1(), data.getT2()))
                .flatMap(bookRepository::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.findBookById(id)
                .flatMap(bookRepository::delete);
    }

    private Book prepareBook(Book oldBook, String name, Author author, Genre genre) {
        var id = oldBook == null ? null : oldBook.getId();
        var comments = oldBook == null ? new ArrayList<CommentBook>() : oldBook.getBookComments();
        return new Book(id, name, author, genre, comments);
    }

}
