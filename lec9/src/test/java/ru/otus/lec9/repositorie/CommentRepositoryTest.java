package ru.otus.lec9.repositorie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import ru.otus.lec9.domain.CommentBook;
import ru.otus.lec9.repositorie.comment.CommentBookRepositoryJpa;
import ru.otus.lec9.repositorie.comment.CommentRepository;
import ru.otus.lec9.util.MockEntityUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CommentBookRepositoryJpa.class)
public class CommentRepositoryTest {

    public final static long COMMENT_ID = 1L;

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Поиск комментария по ID")
    public void findCommentByIdTest() {
        CommentBook comment = repository.findCommentById(COMMENT_ID)
                .orElse(new CommentBook());
        CommentBook mockComment = MockEntityUtil.getCommentBook();
        assertThat(comment)
                .isNotNull()
                .matches(commentBook -> commentBook.getId() == mockComment.getId())
                .matches(commentBook -> commentBook.getText().equals(mockComment.getText()));
    }

    @Test
    @DisplayName("Все комментарии")
    public void getAllTest() {
        List<CommentBook> comments = repository.getAll();
        CommentBook mockComment = MockEntityUtil.getCommentBook();

        assertThat(comments)
                .isNotNull()
                .hasSize(2)
                .anyMatch(comment -> comment.getId() == mockComment.getId())
                .anyMatch(comment -> comment.getText().equals(mockComment.getText()))
                .allMatch(comment -> comment.getBook() != null);
    }

    @Test
    @DisplayName("Добавление нового комментария")
    @Rollback
    public void insertCommentTest() {
        CommentBook newComment = new CommentBook(0L, "test1", MockEntityUtil.getBook());
        repository.save(newComment);
        CommentBook comment = repository.findCommentById(newComment.getId())
                .orElse(new CommentBook());

        assertThat(comment)
                .isNotNull()
                .matches(commentBook -> commentBook.getId() == newComment.getId())
                .matches(commentBook -> commentBook.getText().equals("test1"))
                .matches(commentBook -> commentBook.getBook().equals(MockEntityUtil.getBook()));
    }

    @Test
    @DisplayName("Редактирование комментария")
    @Rollback
    public void updateCommentTest() {
        CommentBook oldComment = repository.findCommentById(COMMENT_ID)
                .orElse(new CommentBook());
        CommentBook comment = new CommentBook(oldComment.getId(), "test1", oldComment.getBook());
        repository.update(comment);
        CommentBook newComment = repository.findCommentById(oldComment.getId())
                .orElse(new CommentBook());

        assertThat(newComment)
                .isNotNull()
                .matches(commentBook -> commentBook.getId() == oldComment.getId())
                .matches(commentBook -> commentBook.getText().equals("test1"))
                .matches(commentBook -> commentBook.getBook().equals(oldComment.getBook()));
    }

    @Test
    @DisplayName("Удаление комментария")
    @Rollback
    public void deleteBookTest() {
        CommentBook comment = repository.findCommentById(COMMENT_ID).orElse(new CommentBook());
        assertThat(repository.findCommentById(COMMENT_ID).isPresent()).isEqualTo(true);
        repository.delete(comment);
        assertThat(repository.findCommentById(COMMENT_ID).isPresent()).isEqualTo(false);
    }

}
