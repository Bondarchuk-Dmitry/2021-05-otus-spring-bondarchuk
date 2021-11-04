package ru.otus.lec20.monock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.lec20.domain.Author;
import ru.otus.lec20.domain.Book;
import ru.otus.lec20.domain.CommentBook;
import ru.otus.lec20.domain.Genre;

import java.util.List;

@ChangeLog
public class DatabaseChangelogTest {

    public final static Author AUTHOR_PUSHKIN = new Author("61601c4040cb0c51c118704a", "Александр", "Пушкин");
    public final static Author AUTHOR_HORSTMAN = new Author("61601c1540cb0c51c1187048", "Кей", "Хорстман");

    public final static Genre GENRE_POETRY = new Genre("61601c6f6592350e22a07fbd", "Поэзия");
    public final static Genre GENRE_NON_FICTION_LITERATURE = new Genre("61601c5e6592350e22a07fbb", "Нехудожественная литература");

    public final static CommentBook COMMENT_BOOK_TEST1_FOR_HORSTMAN = new CommentBook("test1");
    public final static CommentBook COMMENT_BOOK_TEST2_FOR_HORSTMAN = new CommentBook("test2");

    @ChangeSet(order = "001", id = "dropDb", author = "dmitriy.bondarchuk", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "dmitriy.bondarchuk")
    public void insertAuthors(MongoOperations mongoOperation) {
        mongoOperation.save(AUTHOR_PUSHKIN);
        mongoOperation.save(AUTHOR_HORSTMAN);
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "dmitriy.bondarchuk")
    public void insertGenres(MongoOperations mongoOperation) {
        mongoOperation.save(GENRE_POETRY);
        mongoOperation.save(GENRE_NON_FICTION_LITERATURE);
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "dmitriy.bondarchuk")
    public void insertBooks(MongoOperations mongoOperation) {
        mongoOperation.save(new Book("615fffca547bf907bda74ed5",
                "Java. Библиотека профессионала. Том 1. Основы",
                AUTHOR_HORSTMAN,
                GENRE_NON_FICTION_LITERATURE,
                List.of(COMMENT_BOOK_TEST1_FOR_HORSTMAN, COMMENT_BOOK_TEST2_FOR_HORSTMAN)
        ));
    }
}
