package ru.otus.lec20.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestSaveBook {

    private String name;

    private String authorId;

    private String genreId;

}
