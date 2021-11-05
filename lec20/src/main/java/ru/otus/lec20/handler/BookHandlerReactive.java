package ru.otus.lec20.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.lec20.model.RequestSaveBook;
import ru.otus.lec20.service.book.BookService;

import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@RequiredArgsConstructor
public class BookHandlerReactive implements BookHandler {

    private final BookService bookService;

    @Override
    public Mono<ServerResponse> findAll() {
        return bookService.findAll()
                .collectList()
                .flatMap(ok()::bodyValue);
    }

    @Override
    public Mono<ServerResponse> findById(ServerRequest request) {
        var id = request.pathVariable("id");
        return bookService.findBookById(id)
                .flatMap(ok()::bodyValue);
    }

    @Override
    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(RequestSaveBook.class)
                .flatMap(req -> bookService.save(req.getName(), req.getAuthorId(), req.getGenreId()))
                .flatMap(ok()::bodyValue);
    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        var id = request.pathVariable("id");
        return request.bodyToMono(RequestSaveBook.class)
                .flatMap(req -> bookService.update(id, req.getName(), req.getAuthorId(), req.getGenreId()))
                .flatMap(ok()::bodyValue);
    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        var id = request.pathVariable("id");
        return bookService.delete(id)
                .flatMap(__ -> noContent().build());
    }

    @Override
    public Mono<ServerResponse> addComment(ServerRequest request) {
        var id = request.pathVariable("id");
        return request.bodyToMono(String.class)
                .flatMap(text -> bookService.addComment(id, text))
                .flatMap(ok()::bodyValue);
    }

}
