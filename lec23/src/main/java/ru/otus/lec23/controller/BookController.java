package ru.otus.lec23.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lec23.domain.Book;
import ru.otus.lec23.model.RequestSaveBook;
import ru.otus.lec23.service.book.BookService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping("api/books")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @PostMapping("api/books")
    public List<Book> add(@RequestBody RequestSaveBook book) {
        System.out.println(book);
        bookService.save(book.getName(), book.getAuthorId(), book.getGenreId());
        return bookService.getAll();
    }

    @GetMapping("api/books/{id}")
    public Book edit(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PutMapping("api/books/{bookId}")
    public List<Book> edit(@PathVariable long bookId,
                           @RequestBody RequestSaveBook book
    ) {
        bookService.update(bookId, book.getName(), book.getAuthorId(), book.getGenreId());
        return bookService.getAll();
    }

    @DeleteMapping("api/books/{id}")
    public void delete(@PathVariable long id) {
        bookService.delete(id);
    }

}
