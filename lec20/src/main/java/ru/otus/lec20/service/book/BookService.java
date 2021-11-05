package ru.otus.lec20.service.book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lec20.domain.Book;

public interface BookService {

    Mono<Book> findBookById(String id);

    Flux<Book> findAll();

    Mono<Book> save(String name, String authorId, String genreId);

    Mono<Book> addComment(String bookId, String text);

    Mono<Book> update(String id, String name, String authorId, String genreId);

    Mono<Void> delete(String id);

}
