package ru.otus.lec20.handler.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import ru.otus.lec20.service.author.AuthorService;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@RequiredArgsConstructor
public class AuthorHandlerReactive implements AuthorHandler {

    private final AuthorService authorService;

    @Override
    public Mono<ServerResponse> findAll() {
        return authorService.findAll()
                .collectList()
                .flatMap(ok()::bodyValue);
    }
}
