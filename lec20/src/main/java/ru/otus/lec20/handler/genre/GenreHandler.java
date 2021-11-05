package ru.otus.lec20.handler.genre;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface GenreHandler {

    Mono<ServerResponse> findAll();

}

