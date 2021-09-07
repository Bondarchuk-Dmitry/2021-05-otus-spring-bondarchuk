package ru.otus.lec7.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lec7.domain.dto.BookDto;
import ru.otus.lec7.service.book.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService bookService;

    @ShellMethod(key = {"fb", "findBook"}, value = "Поиск книги (Пример: fb {id})")
    public BookDto findBook(@ShellOption({"id", "bookId"}) long id) {
        return bookService.findBookById(id);
    }

    @ShellMethod(key = {"ab", "allBook"}, value = "Поиск книги (Пример: ab)")
    public List<BookDto> getAllBook() {
        return bookService.getAll();
    }

    @ShellMethod(key = {"ib", "insertBook"}, value = "Добавить новую книгу (Пример: ib {bookName} {authorId} {genreId})")
    public String insertBook(@ShellOption String bookName,
                             @ShellOption long authorId,
                             @ShellOption long genreId) {
        Long id = bookService.insert(bookName, authorId, genreId);
        return "Книга успешно добавлена. Id = " + id;
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
        bookService.deleteBookById(id);
        return "Книга успешно удалена. Id = " + id;
    }
}
