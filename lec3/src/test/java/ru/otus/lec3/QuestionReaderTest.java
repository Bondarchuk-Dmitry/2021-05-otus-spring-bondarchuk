package ru.otus.lec3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import ru.otus.lec3.service.exception.ReaderException;
import ru.otus.lec3.service.reader.QuestionReader;
import ru.otus.lec3.service.reader.impl.QuestionReaderCsvImpl;
import ru.otus.lec3.service.translate.LocalizeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class QuestionReaderTest {

    @Autowired
    private QuestionReader questionReader;

    @Autowired
    private LocalizeService msgService;

    @Value("classpath:bad_questions.csv")
    private Resource badResource;

    @Test
    @DisplayName("Кол-во вопросов")
    public void whenGetQuestions_thenResultSize() {
        assertEquals(questionReader.readQuestions().size(), 3);
    }

    @Test
    @DisplayName("Ошибка чтения тестов")
    public void itShouldThrowReaderExceptionWhenBadCsvFile() {
        QuestionReader reader = new QuestionReaderCsvImpl(badResource, msgService);
        ReaderException th = assertThrows(ReaderException.class, reader::readQuestions);
        assertEquals(th.getMessage(), "Ошибка: Не удалось прочитать тесты");
    }

}
