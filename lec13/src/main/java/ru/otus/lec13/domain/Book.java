package ru.otus.lec13.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    public Book (Long id, String name, Long authorId, Long genreId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";

    @Id
    private Long id;

    private String name;

    private Long authorId;

    private Long genreId;

    private List<CommentBook> bookComments;

    private Author author;

    private Genre genre;
}
