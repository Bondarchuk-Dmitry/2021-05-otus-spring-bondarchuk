package ru.otus.lec20.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface BookHandler {
    Mono<ServerResponse> findAll();
    Mono<ServerResponse> findById(ServerRequest request);
    Mono<ServerResponse> save(ServerRequest request);
    Mono<ServerResponse> update(ServerRequest request);
    Mono<ServerResponse> delete(ServerRequest request);
    Mono<ServerResponse> addComment(ServerRequest request);
}
