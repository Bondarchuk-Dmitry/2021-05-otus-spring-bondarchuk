package ru.otus.lec5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
}
