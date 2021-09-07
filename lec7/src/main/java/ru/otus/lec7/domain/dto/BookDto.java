package ru.otus.lec7.domain.dto;

import lombok.Builder;
import lombok.Data;
import ru.otus.lec7.domain.Author;
import ru.otus.lec7.domain.Genre;

@Data
@Builder
public class BookDto {
    private Long id;
    private String name;
    private Author author;
    private Genre genre;

    @Override
    public String toString() {
        return "\u001B[33mНазвание книги:\u001B[0m " + name + System.lineSeparator() +
                "\u001B[33mАвтор:\u001B[0m " + author.getFirstName() + " " + author.getLastName() + System.lineSeparator() +
                "\u001B[33mЖанр:\u001B[0m " + genre.getName() + System.lineSeparator();
    }
}
