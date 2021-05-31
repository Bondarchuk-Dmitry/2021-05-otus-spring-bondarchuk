package ru.otus.lec1.service.reader.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import ru.otus.lec1.model.Question;
import ru.otus.lec1.service.exception.ReaderException;
import ru.otus.lec1.service.reader.QuestionReader;

import java.io.IOException;
import java.util.List;

public class QuestionJsonReaderImpl implements QuestionReader {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Question> readQuestions(Resource resource) {
        try {
            return mapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<>() {
                    }
            );
        } catch (IOException e) {
            throw new ReaderException("Не удалось прочитать тесты");
        }
    }
}
