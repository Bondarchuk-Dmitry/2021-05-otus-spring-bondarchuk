package ru.otus.lec16.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestSaveBook {

    private Long id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    private String name;

    private Long authorId;

    private Long genreId;

}
