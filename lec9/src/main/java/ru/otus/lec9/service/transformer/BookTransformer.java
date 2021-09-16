package ru.otus.lec9.service.transformer;

import ru.otus.lec9.domain.Book;

import java.util.List;

public interface BookTransformer {

    String getTransformBook(Book book);
    String getTransformAllBook(List<Book> books);
}
