package ru.otus.lec13.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.service.book.BookService;
import ru.otus.lec13.service.transformer.BookTransformer;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService bookService;
    private final BookTransformer bookTransformer;

    @ShellMethod(key = {"fb", "findBook"}, value = "Поиск книги (Пример: fb {id})")
    public String findBook(@ShellOption({"id", "bookId"}) String id) {
        Book book = bookService.findBookById(id);
        return bookTransformer.getTransformBook(book) + bookTransformer.getTransformAllComments(book.getBookComments());
    }

    @ShellMethod(key = {"ab", "allBook"}, value = "Поиск всех книг (Пример: ab)")
    public String getAllBook() {
        return bookTransformer.getTransformAllBook(bookService.getAll());
    }

    @ShellMethod(key = {"ib", "insertBook"}, value = "Добавить новую книгу (Пример: ib {bookName} {authorId} {genreId})")
    public String insertBook(@ShellOption String bookName,
                             @ShellOption String authorId,
                             @ShellOption String genreId) {
        Book book = bookService.save(bookName, authorId, genreId);
        return "Книга успешно добавлена. Id = " + book.getId();
    }

    @ShellMethod(key = {"ub", "updateBook"}, value = "Редактировать информацию о книге (Пример: ub {id} {bookName} {authorId} {genreId})")
    public String updateBook(@ShellOption String id,
                             @ShellOption String bookName,
                             @ShellOption String authorId,
                             @ShellOption String genreId) {
        bookService.update(id, bookName, authorId, genreId);
        return "Книга успешно отредактирована. Id = " + id;
    }

    @ShellMethod(key = {"db", "deleteBook"}, value = "Удалить книгу (Пример: db {id})")
    public String deleteBook(@ShellOption({"id", "bookId"}) String id) {
        bookService.delete(id);
        return "Книга успешно удалена. Id = " + id;
    }

    @ShellMethod(key = {"ic", "insertComment"}, value = "Добавить комментарий к книге (Пример: iс {text} {bookId})")
    public String insertComment(@ShellOption String bookId,
                                @ShellOption String text) {
        Book book = bookService.addComment(bookId, text);
        return String.format("Комментарий для книги id = %s успешно добавлен.", book.getId());
    }
}
