package ru.otus.lec7.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec7.dao.author.AuthorDao;
import ru.otus.lec7.domain.Author;
import ru.otus.lec7.exception.AuthorNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public Author findAuthorById(Long id) {
        return authorDao.findAuthorById(id)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Автор по id %d не найден", id)));
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

}
