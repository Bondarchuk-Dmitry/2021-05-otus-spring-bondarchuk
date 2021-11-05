package ru.otus.lec20.handler.author;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface AuthorHandler {

    Mono<ServerResponse> findAll();
}
