package ru.otus.lec2.service.reader;

import ru.otus.lec2.model.Question;

import java.util.List;

public interface QuestionReader {
    List<Question> readQuestions();
}
