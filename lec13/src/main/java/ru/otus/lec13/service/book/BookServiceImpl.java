package ru.otus.lec13.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec13.domain.Author;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.domain.Genre;
import ru.otus.lec13.exception.BookNotFoundException;
import ru.otus.lec13.repositorie.book.BookRepository;
import ru.otus.lec13.repositorie.comment.CommentRepository;
import ru.otus.lec13.service.author.AuthorService;
import ru.otus.lec13.service.generator.SequenceGenerator;
import ru.otus.lec13.service.genre.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentRepository commentRepository;
    private final SequenceGenerator seqGenerator;

    @Override
    public Book findBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        return this.prepareBook(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll().stream()
                .map(this::prepareBook)
                .collect(Collectors.toList());
    }

    @Override
    public Book save(String name, Long authorId, Long genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book book = prepareBook(seqGenerator.getNextId(Book.SEQUENCE_NAME), name, author.getId(), genre.getId());
        return bookRepository.save(book);
    }

    @Override
    public void update(Long id, String name, Long authorId, Long genreId) {
        Author author = authorService.findAuthorById(authorId);
        Genre genre = genreService.findGenreById(genreId);
        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        Book newBook = prepareBook(oldBook.getId(), name, author.getId(), genre.getId());
        bookRepository.save(newBook);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Книга по id %d не найдена", id)));
        bookRepository.delete(book);
    }

    private Book prepareBook(Long id, String name, Long authorId, Long genreId) {
        return new Book(id, name, authorId, genreId);
    }

    private Book prepareBook(Book book) {
        book.setAuthor(authorService.findAuthorById(book.getAuthorId()));
        book.setGenre(genreService.findGenreById(book.getGenreId()));
        book.setBookComments(commentRepository.findCommentBookByBookId(book.getId()));
        return book;
    }

}
