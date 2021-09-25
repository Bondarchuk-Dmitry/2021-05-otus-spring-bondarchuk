package ru.otus.lec13.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec13.domain.Book;
import ru.otus.lec13.domain.CommentBook;
import ru.otus.lec13.exception.CommentNotFoundException;
import ru.otus.lec13.repositorie.comment.CommentRepository;
import ru.otus.lec13.service.book.BookService;
import ru.otus.lec13.service.generator.SequenceGenerator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentBookServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookService bookService;
    private final SequenceGenerator seqGenerator;

    @Override
    public CommentBook findCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(String.format("Комментарий по id %d не найдена", id)));
    }

    @Override
    public List<CommentBook> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public CommentBook save(String text, long bookId) {
        Book book = bookService.findBookById(bookId);
        return commentRepository.save(new CommentBook(seqGenerator.getNextId(CommentBook.SEQUENCE_NAME), text, book.getId()));
    }

    @Override
    public void update(long id, String text, long bookId) {
        Book book = bookService.findBookById(bookId);
        commentRepository.save(new CommentBook(id, text, book.getId()));
    }

    @Override
    public void delete(long id) {
        CommentBook comment = this.findCommentById(id);
        commentRepository.delete(comment);
    }
}
