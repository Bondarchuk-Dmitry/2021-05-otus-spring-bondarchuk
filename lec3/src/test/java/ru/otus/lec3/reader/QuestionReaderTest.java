package ru.otus.lec3.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lec3.configuration.ReaderProperties;
import ru.otus.lec3.service.exception.ReaderException;
import ru.otus.lec3.service.reader.QuestionReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionReaderTest {

    @Autowired
    private QuestionReader reader;

    @MockBean
    private ReaderProperties readerProperties;

    @BeforeEach
    void init() {
        when(readerProperties.getFilePathLocale()).thenReturn("questions.csv");
        when(readerProperties.getFilePathDefault()).thenReturn("questions.csv");
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

    @Test
    @DisplayName("Ошибка чтения тестов")
    public void itShouldThrowReaderExceptionWhenBadCsvFile() {
        when(readerProperties.getFilePathLocale()).thenReturn("bad_questions.csv");
        ReaderException th = assertThrows(ReaderException.class, reader::readQuestions);
        assertEquals(th.getMessage(), "Ошибка: Не удалось прочитать тесты");
    }
}
