package ru.otus.lec3.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.lec3.service.reader.QuestionReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuestionReaderTest {

    @Autowired
    private QuestionReader reader;

    @Test
    @DisplayName("Кол-во вопросов")
    public void whenGetQuestions_thenResultSize() {
        assertEquals(reader.readQuestions().size(), 3);
    }

    @Test
    @DisplayName("Список вопросов не пустой")
    public void whenGetQuestions_thenResultIsNotNull() {
        reader.readQuestions().forEach(Assertions::assertNotNull);
    }

    @Test
    @DisplayName("Вопросы не пустые")
    public void whenGetQuestionsGetQuestion_thenResultIsNotNull() {
        reader.readQuestions().forEach(question -> assertNotNull(question.getQuestion()));
    }

    @Test
    @DisplayName("Ответы не пустые")
    public void whenGetQuestionsGetAnswer_thenResultIsNotNull() {
        reader.readQuestions().forEach(question -> assertNotNull(question.getCorrectAnswer()));
    }
}
