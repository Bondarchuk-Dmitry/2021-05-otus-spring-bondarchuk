package ru.otus.lec9.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lec9.domain.Book;
import ru.otus.lec9.domain.CommentBook;
import ru.otus.lec9.exception.CommentNotFoundException;
import ru.otus.lec9.repositorie.comment.CommentRepository;
import ru.otus.lec9.service.book.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentBookServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public CommentBook findCommentById(long id) {
        return commentRepository.findCommentById(id)
                .orElseThrow(() -> new CommentNotFoundException(String.format("Комментарий по id %d не найдена", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentBook> getAll() {
        return commentRepository.getAll();
    }

    @Override
    @Transactional
    public CommentBook save(String text, long bookId) {
        Book book = bookService.findBookById(bookId);
        return commentRepository.save(new CommentBook(0L, text, book));
    }

    @Override
    @Transactional
    public void update(long id, String text, long bookId) {
        Book book = bookService.findBookById(bookId);
        commentRepository.update(new CommentBook(id, text, book));
    }

    @Override
    @Transactional
    public void delete(long id) {
        CommentBook comment = this.findCommentById(id);
        commentRepository.delete(comment);
    }
}
