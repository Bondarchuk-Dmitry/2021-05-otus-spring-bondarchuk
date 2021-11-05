package ru.otus.lec20.handler.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.lec20.service.genre.GenreService;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@RequiredArgsConstructor
public class GenreHandlerReactive implements GenreHandler {

    private final GenreService genreService;

    @Override
    public Mono<ServerResponse> findAll() {
        return genreService.findAll()
                .collectList()
                .flatMap(ok()::bodyValue);
    }


}
