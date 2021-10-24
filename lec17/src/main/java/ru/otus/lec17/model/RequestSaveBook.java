package ru.otus.lec17.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestSaveBook {

    private Long id;

    private String name;

    private Long authorId;

    private Long genreId;

}
