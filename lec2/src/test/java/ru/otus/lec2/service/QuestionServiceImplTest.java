package ru.otus.lec2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import ru.otus.lec2.service.reader.QuestionReader;
import ru.otus.lec2.service.reader.impl.QuestionReaderCsvImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class QuestionServiceImplTest {

    private QuestionReader reader;

    @BeforeEach
    void init() {
        Resource res = new ClassPathResource("questions.csv");
        this.reader = new QuestionReaderCsvImpl(res);
    }

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
