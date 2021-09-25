package ru.otus.lec13.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.lec13.domain.Author;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.domain.CommentBook;
import ru.otus.lec13.domain.Genre;
import ru.otus.lec13.repositorie.author.AuthorRepository;
import ru.otus.lec13.repositorie.book.BookRepository;
import ru.otus.lec13.repositorie.comment.CommentRepository;
import ru.otus.lec13.repositorie.genre.GenreRepository;
import ru.otus.lec13.service.generator.SequenceGenerator;

import java.util.List;

@ChangeLog
public class DatabaseChangelogTest {

    public final static Author AUTHOR_PUSHKIN = new Author(1L, "Александр", "Пушкин");
    public final static Author AUTHOR_HORSTMAN = new Author(2L, "Кей", "Хорстман");

    public final static Genre GENRE_POETRY = new Genre(1L, "Поэзия");
    public final static Genre GENRE_NON_FICTION_LITERATURE = new Genre(2L, "Нехудожественная литература");

    public final static CommentBook COMMENT_BOOK_TEST1_FOR_HORSTMAN = new CommentBook(1L, "test1", 1L);
    public final static CommentBook COMMENT_BOOK_TEST2_FOR_HORSTMAN = new CommentBook(2L, "test2", 1L);

    @ChangeSet(order = "001", id = "dropDb", author = "dmitriy.bondarchuk", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "dmitriy.bondarchuk")
    public void insertAuthors(AuthorRepository repository) {
        repository.saveAll(List.of(AUTHOR_PUSHKIN, AUTHOR_HORSTMAN));
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "dmitriy.bondarchuk")
    public void insertGenres(GenreRepository repository) {
        repository.saveAll(List.of(GENRE_POETRY, GENRE_NON_FICTION_LITERATURE));
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "dmitriy.bondarchuk")
    public void insertBooks(BookRepository repository, SequenceGenerator seqGenerator) {
        repository.save(new Book(seqGenerator.getNextId(Book.SEQUENCE_NAME),
                "Java. Библиотека профессионала. Том 1. Основы",
                AUTHOR_HORSTMAN.getId(),
                GENRE_NON_FICTION_LITERATURE.getId()
        ));
    }

    @ChangeSet(order = "005", id = "insertComments", author = "dmitriy.bondarchuk")
    public void insertComment(CommentRepository repository) {
        repository.saveAll(List.of(COMMENT_BOOK_TEST1_FOR_HORSTMAN, COMMENT_BOOK_TEST2_FOR_HORSTMAN));
    }
}
