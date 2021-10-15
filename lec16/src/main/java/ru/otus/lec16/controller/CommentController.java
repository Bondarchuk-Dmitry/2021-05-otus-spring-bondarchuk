package ru.otus.lec16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.lec16.service.comment.CommentService;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @PostMapping("comment")
    public RedirectView addComment(
            @RequestParam("id") long bookId,
            String commentText
    ) {
        commentService.save(commentText, bookId);
        return new RedirectView("/book/edit?id=" + bookId);
    }
}
