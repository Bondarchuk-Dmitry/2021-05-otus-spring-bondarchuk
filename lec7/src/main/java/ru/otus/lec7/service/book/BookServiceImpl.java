package ru.otus.lec7.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec7.dao.book.BookDao;
import ru.otus.lec7.domain.Author;
import ru.otus.lec7.domain.Book;
import ru.otus.lec7.domain.Genre;
import ru.otus.lec7.domain.dto.BookDto;
import ru.otus.lec7.exception.BookNotFoundException;
import ru.otus.lec7.service.author.AuthorService;
import ru.otus.lec7.service.genre.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public BookDto findBookById(Long id) {
        Book book = bookDao.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        return this.getBookDto(book);
    }

    @Override
    public List<BookDto> getAll() {
        return bookDao.getAll()
                .stream()
                .map(this::getBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long insert(String name, Long authorId, Long genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book book = prepareBook(0L, name, author.getId(), genre.getId());
        bookDao.insert(book);
        return bookDao.lastId();
    }

    @Override
    public void update(Long id, String name, Long authorId, Long genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book oldBook = bookDao.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        Book newBook = prepareBook(oldBook.getId(), name, author.getId(), genre.getId());
        bookDao.update(newBook);
    }

    @Override
    public void deleteBookById(Long id) {
        Book book = bookDao.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        bookDao.deleteById(book.getId());
    }

    private BookDto getBookDto(Book book) {
        Author author = authorService.findAuthorById(book.getAuthorId());
        Genre genre = genreService.findGenreById(book.getGenreId());
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(author)
                .genre(genre)
                .build();
    }

    private Book prepareBook(Long id, String name, Long authorId, Long genreId) {
        return Book.builder()
                .id(id)
                .name(name)
                .authorId(authorId)
                .genreId(genreId)
                .build();
    }

}
