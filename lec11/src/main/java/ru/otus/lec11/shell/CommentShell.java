package ru.otus.lec11.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lec11.domain.CommentBook;
import ru.otus.lec11.service.comment.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentShell {

    private final CommentService commentService;

    @ShellMethod(key = {"fс", "findComment"}, value = "Поиск комментария по ID (Пример: fc {id})")
    public CommentBook findComment(@ShellOption({"id", "bookId"}) long id) {
        return commentService.findCommentById(id);
    }

    @ShellMethod(key = {"ac", "allComment"}, value = "Поиск всех комментариев (Пример: ac)")
    public List<CommentBook> getAllComment() {
        return commentService.getAll();
    }

    @ShellMethod(key = {"ic", "insertComment"}, value = "Добавить комментарий к книге (Пример: iс {text} {bookId})")
    public String insertComment(@ShellOption String text,
                                @ShellOption long bookId) {
        CommentBook comment = commentService.save(text, bookId);
        return "Комментарий успешно добавлен. Id = " + comment.getId();
    }

    @ShellMethod(key = {"uc", "updateComment"}, value = "Редактировать комментария к книге (Пример: uc {id} {text} {bookId})")
    public String updateBook(@ShellOption long id,
                             @ShellOption String text,
                             @ShellOption long bookId) {
        commentService.update(id, text, bookId);
        return "Комментарий успешно отредактирован. Id = " + id;
    }

    @ShellMethod(key = {"dc", "deleteComment"}, value = "Удалить комментарий (Пример: dc {id})")
    public String deleteBook(@ShellOption({"id", "bookId"}) long id) {
        commentService.delete(id);
        return "Комментарий успешно удален. Id = " + id;
    }
}
