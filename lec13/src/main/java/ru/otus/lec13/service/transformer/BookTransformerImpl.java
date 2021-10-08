package ru.otus.lec13.service.transformer;

import org.springframework.stereotype.Service;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.domain.CommentBook;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookTransformerImpl implements BookTransformer {
    @Override
    public String getTransformBook(Book book) {
        return "\u001B[33mID книги:\u001B[0m " + book.getId() + System.lineSeparator() +
                "\u001B[33mНазвание книги:\u001B[0m " + book.getName() + System.lineSeparator() +
                "\u001B[33mАвтор:\u001B[0m " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() + System.lineSeparator() +
                "\u001B[33mЖанр:\u001B[0m " + book.getGenre().getName();
    }

    @Override
    public String getTransformAllBook(List<Book> books) {
        return books.stream()
                .map(this::getTransformBook)
                .collect(Collectors.joining("\n---------\n"));
    }

    public String getTransformAllComments(List<CommentBook> comments) {
        return comments.isEmpty()
                ? ""
                : comments.stream()
                .map(CommentBook::getText)
                .collect(Collectors.joining(";\n\t* ",
                        "\n\u001B[33mКомментарии к книге:\u001B[0m\n\t* ",
                        ";"));

    }
}
