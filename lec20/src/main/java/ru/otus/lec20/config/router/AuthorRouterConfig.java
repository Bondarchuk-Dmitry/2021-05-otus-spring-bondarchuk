package ru.otus.lec20.config.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.lec20.handler.author.AuthorHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AuthorRouterConfig {

    @Bean
    RouterFunction<ServerResponse> authorRoutes(AuthorHandler authorHandlerReactive) {
        return route()
                .path("api", builder ->
                        builder
                                .GET("/authors", __ -> authorHandlerReactive.findAll()))
                .build();


    }
}
