package ru.otus.lec13.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.lec13.domain.Author;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.domain.Genre;
import ru.otus.lec13.repositorie.author.AuthorRepository;
import ru.otus.lec13.repositorie.book.BookRepository;
import ru.otus.lec13.repositorie.genre.GenreRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    public final static Author AUTHOR_PUSHKIN = new Author("1", "Александр", "Пушкин");
    public final static Author AUTHOR_HORSTMAN = new Author("2", "Кей", "Хорстман");

    public final static Genre GENRE_POETRY = new Genre("1", "Поэзия");
    public final static Genre GENRE_NON_FICTION_LITERATURE = new Genre("2", "Нехудожественная литература");

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
    public void insertBooks(BookRepository repository) {
        repository.save(new Book(null,
                "Java. Библиотека профессионала. Том 1. Основы",
                AUTHOR_HORSTMAN,
                GENRE_NON_FICTION_LITERATURE
        ));

    }

}
