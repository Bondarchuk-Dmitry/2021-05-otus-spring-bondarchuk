package ru.otus.lec7.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private Long id;
    private String name;
    private Long authorId;
    private Long genreId;
}
