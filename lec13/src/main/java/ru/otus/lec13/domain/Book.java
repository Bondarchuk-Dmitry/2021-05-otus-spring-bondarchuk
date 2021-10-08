package ru.otus.lec13.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    public Book (String id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book (String id, String name, Author author, Genre genre, List<CommentBook> bookComments) {
        this.id = id;
        this.name = name;
        this.bookComments = bookComments;
        this.author = author;
        this.genre = genre;
    }

    @Id
    private String id;

    private String name;

    private List<CommentBook> bookComments = new ArrayList<>();

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;
}
