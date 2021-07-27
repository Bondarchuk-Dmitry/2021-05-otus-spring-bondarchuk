package ru.otus.lec3.service.reader;

import ru.otus.lec3.model.Question;

import java.util.List;

public interface QuestionReader {
    List<Question> readQuestions();
}
