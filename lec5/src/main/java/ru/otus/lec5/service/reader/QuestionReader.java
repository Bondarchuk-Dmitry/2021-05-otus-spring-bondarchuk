package ru.otus.lec5.service.reader;

import ru.otus.lec5.model.Question;

import java.util.List;

public interface QuestionReader {
    List<Question> readQuestions();
}
