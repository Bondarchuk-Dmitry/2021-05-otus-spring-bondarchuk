package ru.otus.lec20.service.author;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lec20.domain.Author;

public interface AuthorService {

    Mono<Author> findAuthorById(String id);

    Flux<Author> findAll();

}
