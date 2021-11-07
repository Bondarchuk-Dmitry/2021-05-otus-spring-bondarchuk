package ru.otus.lec23.repositorie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import ru.otus.lec23.domain.CommentBook;
import ru.otus.lec23.repositorie.comment.CommentRepository;
import ru.otus.lec23.util.MockEntityUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CommentRepositoryTest {

    public final static long COMMENT_ID = 1L;

    @Autowired
    private CommentRepository repository;

    @Test
    @DisplayName("Поиск комментария по ID")
    public void findCommentByIdTest() {
        CommentBook comment = repository.findById(COMMENT_ID)
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
        List<CommentBook> comments = repository.findAll();
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
        CommentBook comment = repository.getById(newComment.getId());

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
        CommentBook oldComment = repository.getById(COMMENT_ID);
        CommentBook comment = new CommentBook(oldComment.getId(), "test1", oldComment.getBook());
        repository.save(comment);
        CommentBook newComment = repository.getById(oldComment.getId());

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
        CommentBook comment = repository.getById(COMMENT_ID);
        assertThat(repository.findById(COMMENT_ID).isPresent()).isEqualTo(true);
        repository.delete(comment);
        assertThat(repository.findById(COMMENT_ID).isPresent()).isEqualTo(false);
    }

}
