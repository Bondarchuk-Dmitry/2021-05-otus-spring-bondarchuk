package ru.otus.lec9.service.transformer;

import ru.otus.lec9.domain.Book;
import ru.otus.lec9.domain.CommentBook;

import java.util.List;

public interface BookTransformer {

    String getTransformBook(Book book);
    String getTransformAllBook(List<Book> books);
    String getTransformAllComments(List<CommentBook> comments);
}
