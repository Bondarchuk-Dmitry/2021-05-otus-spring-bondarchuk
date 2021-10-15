package ru.otus.lec16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.lec16.model.RequestSaveBook;
import ru.otus.lec16.service.author.AuthorService;
import ru.otus.lec16.service.book.BookService;
import ru.otus.lec16.service.genre.GenreService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("book")
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        if (!model.containsAttribute("newBook")) {
            model.addAttribute("newBook", new RequestSaveBook());
        }
        return "book/books";
    }

    @PostMapping("book/add")
    public RedirectView add(@Valid @ModelAttribute("newBook") RequestSaveBook book,
                      BindingResult result,
                      RedirectAttributes redirectAttrs
    ) {
        if (result.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("org.springframework.validation.BindingResult.newBook", result)
                    .addFlashAttribute("newBook", book);
            return new RedirectView("/book");
        }
        bookService.save(book.getName(), book.getAuthorId(), book.getGenreId());
        return new RedirectView("/book");
    }

    @GetMapping("book/edit")
    public String edit(@RequestParam("id") long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        if (!model.containsAttribute("oldBook")) {
            model.addAttribute("oldBook", new RequestSaveBook());
        }
        return "book/edit";
    }

    @PostMapping("book/edit")
    public RedirectView edit(@RequestParam("id") long bookId,
                             @Valid @ModelAttribute("oldBook") RequestSaveBook book,
                             BindingResult result,
                             RedirectAttributes redirectAttrs
    ) {
        if (result.hasErrors()) {
            redirectAttrs
                    .addAttribute("id", bookId)
                    .addFlashAttribute("org.springframework.validation.BindingResult.oldBook", result)
                    .addFlashAttribute("oldBook", book);
            return new RedirectView("/book/edit?id={id}");
        }
        bookService.update(bookId, book.getName(), book.getAuthorId(), book.getGenreId());
        return new RedirectView("/book");
    }

    @DeleteMapping("book/delete")
    public RedirectView delete(@RequestParam("id") long id) {
        bookService.delete(id);
        return new RedirectView("/book");
    }

}
