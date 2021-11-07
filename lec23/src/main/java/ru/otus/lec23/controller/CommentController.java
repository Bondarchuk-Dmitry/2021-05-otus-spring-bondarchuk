package ru.otus.lec23.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lec23.domain.CommentBook;
import ru.otus.lec23.service.comment.CommentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("api/books/{bookId}/comment")
    public List<CommentBook> addComment(
            @PathVariable("bookId") long bookId,
            @RequestBody String commentText) {
        commentService.save(commentText, bookId);
        return commentService.findCommentByBookId(bookId);
    }
}
