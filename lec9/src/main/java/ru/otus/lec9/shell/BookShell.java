package ru.otus.lec9.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lec9.domain.Book;
import ru.otus.lec9.service.book.BookService;
import ru.otus.lec9.service.transformer.BookTransformer;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService bookService;
    private final BookTransformer bookTransformer;

    @ShellMethod(key = {"fb", "findBook"}, value = "Поиск книги (Пример: fb {id})")
    public String findBook(@ShellOption({"id", "bookId"}) long id) {
        return bookTransformer.getTransformBook(bookService.findBookById(id));
    }

    @ShellMethod(key = {"ab", "allBook"}, value = "Поиск всех книг (Пример: ab)")
    public String getAllBook() {
        return bookTransformer.getTransformAllBook(bookService.getAll());
    }

    @ShellMethod(key = {"ib", "insertBook"}, value = "Добавить новую книгу (Пример: ib {bookName} {authorId} {genreId})")
    public String insertBook(@ShellOption String bookName,
                             @ShellOption long authorId,
                             @ShellOption long genreId) {
        Book book = bookService.save(bookName, authorId, genreId);
        return "Книга успешно добавлена. Id = " + book.getId();
    }

    @ShellMethod(key = {"ub", "updateBook"}, value = "Редактировать информацию о книге (Пример: ub {id} {bookName} {authorId} {genreId})")
    public String updateBook(@ShellOption long id,
                             @ShellOption String bookName,
                             @ShellOption long authorId,
                             @ShellOption long genreId) {
        bookService.update(id, bookName, authorId, genreId);
        return "Книга успешно отредактирована. Id = " + id;
    }

    @ShellMethod(key = {"db", "deleteBook"}, value = "Удалить книгу (Пример: db {id})")
    public String deleteBook(@ShellOption({"id", "bookId"}) long id) {
        bookService.delete(id);
        return "Книга успешно удалена. Id = " + id;
    }
}
