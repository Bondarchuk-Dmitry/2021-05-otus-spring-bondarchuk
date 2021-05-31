package ru.otus.lec1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.lec1.service.reader.QuestionReader;
import ru.otus.lec1.service.reader.impl.QuestionJsonReaderImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    QuestionService questionService;

    @BeforeEach
    void init() {
        Resource res = new ClassPathResource("questions.csv");
        QuestionReader reader = new QuestionJsonReaderImpl();
        when(questionService.getQuestions())
                .thenReturn(reader.readQuestions(res));
    }

    @Test
    @DisplayName("Кол-во вопросов")
    public void whenGetQuestions_thenResultSize() {
        assertEquals(questionService.getQuestions().size(), 3);
    }
}
