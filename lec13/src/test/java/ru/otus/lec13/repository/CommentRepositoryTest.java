package ru.otus.lec13.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.lec13.domain.CommentBook;
import ru.otus.lec13.repositorie.comment.CommentRepository;
import ru.otus.lec13.util.MockEntityUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentRepositoryTest {

    public final static long COMMENT_ID = 1L;

    @Autowired
    private CommentRepository repository;

    @Test
    @DisplayName("Поиск комментария по ID")
    public void findCommentByIdTest() {
        CommentBook comment = repository.findById(COMMENT_ID)
                .orElse(new CommentBook());
        CommentBook mockComment = MockEntityUtil.getCommentBookTest1();
        assertThat(comment)
                .isNotNull()
                .matches(commentBook -> commentBook.getId().equals(mockComment.getId()))
                .matches(commentBook -> commentBook.getText().equals(mockComment.getText()));
    }

    @Test
    @DisplayName("Все комментарии")
    public void getAllTest() {
        List<CommentBook> comments = repository.findAll();
        CommentBook mockComment = MockEntityUtil.getCommentBookTest1();

        assertThat(comments)
                .isNotNull()
                .hasSize(2)
                .anyMatch(comment -> comment.getId().equals(mockComment.getId()))
                .anyMatch(comment -> comment.getText().equals(mockComment.getText()))
                .allMatch(comment -> comment.getBookId() != null);
    }

}
