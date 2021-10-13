package ru.otus.lec13.service.transformer;

import ru.otus.lec13.domain.Book;
import ru.otus.lec13.domain.CommentBook;

import java.util.List;

public interface BookTransformer {

    String getTransformBook(Book book);

    String getTransformAllBook(List<Book> books);

    String getTransformAllComments(List<CommentBook> comments);
}
