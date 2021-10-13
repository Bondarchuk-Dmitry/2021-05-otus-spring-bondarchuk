package ru.otus.lec13.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lec13.domain.Author;
import ru.otus.lec13.exception.AuthorNotFoundException;
import ru.otus.lec13.repositorie.author.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public Author findAuthorById(String id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Автор по id %s не найден", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

}
