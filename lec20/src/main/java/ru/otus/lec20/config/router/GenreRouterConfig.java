package ru.otus.lec20.config.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.lec20.handler.genre.GenreHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GenreRouterConfig {

    @Bean
    RouterFunction<ServerResponse> genreRoutes(GenreHandler genreHandlerReactive) {
        return route()
                .path("api", builder ->
                        builder
                                .GET("/genres", __ -> genreHandlerReactive.findAll()))
                .build();
    }
}
