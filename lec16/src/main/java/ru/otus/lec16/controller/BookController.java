package ru.otus.lec16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.lec16.model.RequestSaveBook;
import ru.otus.lec16.service.author.AuthorService;
import ru.otus.lec16.service.book.BookService;
import ru.otus.lec16.service.comment.CommentService;
import ru.otus.lec16.service.genre.GenreService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        if (!model.containsAttribute("addBook")) {
            model.addAttribute("addBook", new RequestSaveBook());
        }
        return "book/books";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("addBook") RequestSaveBook book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return this.getAll(model);
        }
        bookService.save(book.getName(), book.getAuthorId(), book.getGenreId());
        return this.getAll(model);
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        if (!model.containsAttribute("editBook")) {
            model.addAttribute("editBook", new RequestSaveBook());
        }
        return "book/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       @Valid @ModelAttribute("editBook") RequestSaveBook book,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            return this.edit(id, model);
        }
        bookService.update(id, book.getName(), book.getAuthorId(), book.getGenreId());
        return this.getAll(model);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id, Model model) {
        bookService.delete(id);
        return this.getAll(model);
    }

    @PostMapping("addComment")
    public RedirectView addComment(
            @RequestParam("id") long id,
            String commentText
    ) {
        commentService.save(commentText, id);
        return new RedirectView("/book/edit?id=" + id);
    }
}
