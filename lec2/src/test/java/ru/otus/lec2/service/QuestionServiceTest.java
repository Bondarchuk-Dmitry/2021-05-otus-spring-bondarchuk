package ru.otus.lec2.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.lec2.service.reader.impl.QuestionReaderCsvImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {QuestionReaderCsvImpl.class })
public class QuestionServiceTest {

    @Autowired
    private QuestionReaderCsvImpl questionReaderCsvImpl;

    @Test
    @DisplayName("Кол-во вопросов")
    public void whenGetQuestions_thenResultSize() {
        assertEquals(questionReaderCsvImpl.readQuestions().size(), 3);
    }
}
