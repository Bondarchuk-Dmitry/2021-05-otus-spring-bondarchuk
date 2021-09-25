package ru.otus.lec13.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookComments")
public class CommentBook {

    @Transient
    public static final String SEQUENCE_NAME = "book_comments_sequence";

    @Id
    private Long id;

    private String text;

    private Long bookId;

}
