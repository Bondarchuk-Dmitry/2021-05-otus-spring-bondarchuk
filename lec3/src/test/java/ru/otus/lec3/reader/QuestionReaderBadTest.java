package ru.otus.lec3.reader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.lec3.service.exception.ReaderException;
import ru.otus.lec3.service.reader.QuestionReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("bad")
public class QuestionReaderBadTest {

    @Autowired
    private QuestionReader reader;

    @Test
    @DisplayName("Ошибка чтения тестов")
    public void itShouldThrowReaderExceptionWhenBadCsvFile() {
        ReaderException th = assertThrows(ReaderException.class, reader::readQuestions);
        assertEquals(th.getMessage(), "Ошибка: Не удалось прочитать тесты");
    }
}
