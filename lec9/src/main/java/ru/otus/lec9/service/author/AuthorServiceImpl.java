package ru.otus.lec9.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lec9.repositorie.author.AuthorRepository;
import ru.otus.lec9.domain.Author;
import ru.otus.lec9.exception.AuthorNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public Author findAuthorById(Long id) {
        return authorRepository.findAuthorById(id)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Автор по id %d не найден", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

}
