package ru.otus.lec20.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.lec20.domain.Author;
import ru.otus.lec20.domain.Book;
import ru.otus.lec20.domain.Genre;

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

    @ChangeSet(order = "002", id = "saveAuthors", author = "dmitriy.bondarchuk")
    public void saveAuthors(MongoOperations mongoOperation) {
        mongoOperation.save(AUTHOR_PUSHKIN);
        mongoOperation.save(AUTHOR_HORSTMAN);
    }

    @ChangeSet(order = "003", id = "saveGenres", author = "dmitriy.bondarchuk")
    public void saveGenres(MongoOperations mongoOperation) {
        mongoOperation.save(GENRE_POETRY);
        mongoOperation.save(GENRE_NON_FICTION_LITERATURE);
    }

    @ChangeSet(order = "004", id = "saveBooks", author = "dmitriy.bondarchuk")
    public void saveBooks(MongoOperations mongoOperation) {
        mongoOperation.save(new Book(null,
                "Java. Библиотека профессионала. Том 1. Основы",
                AUTHOR_HORSTMAN,
                GENRE_NON_FICTION_LITERATURE
        ));
    }

}
