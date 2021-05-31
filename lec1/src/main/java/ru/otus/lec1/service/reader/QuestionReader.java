package ru.otus.lec1.service.reader;

import org.springframework.core.io.Resource;
import ru.otus.lec1.model.Question;

import java.util.List;

public interface QuestionReader {
    List<Question> readQuestions(Resource resource);
}
