package ru.otus.lec20.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lec20.domain.Author;
import ru.otus.lec20.exception.AuthorNotFoundException;
import ru.otus.lec20.repositorie.author.AuthorRepository;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Mono<Author> findAuthorById(String id) {
        return authorRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new AuthorNotFoundException(String.format("Автор по id %s не найден", id))));
    }

    @Override
    public Flux<Author> findAll() {
        return authorRepository.findAll();
    }

}
