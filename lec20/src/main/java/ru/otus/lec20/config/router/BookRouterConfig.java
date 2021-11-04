package ru.otus.lec20.config.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.lec20.handler.BookHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouterConfig {

    @Bean
    RouterFunction<ServerResponse> bookRoutes(BookHandler bookHandlerReactive) {
        return route()
                .path("api", builder ->
                        builder
                                .GET("/books", __ -> bookHandlerReactive.findAll())
                                .GET("/books/{id}", bookHandlerReactive::findById)
                                .POST("/books", bookHandlerReactive::save)
                                .PUT("/books/{id}", bookHandlerReactive::update)
                                .DELETE("/books/{id}", bookHandlerReactive::delete)
                                .PUT("/books/{id}/comment", bookHandlerReactive::addComment))
                .build();
    }
}
