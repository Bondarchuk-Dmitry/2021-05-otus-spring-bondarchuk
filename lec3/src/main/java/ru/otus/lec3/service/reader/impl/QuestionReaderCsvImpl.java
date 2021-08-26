package ru.otus.lec3.service.reader.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.lec3.configuration.ReaderProperties;
import ru.otus.lec3.model.Question;
import ru.otus.lec3.service.exception.ReaderException;
import ru.otus.lec3.service.reader.QuestionReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class QuestionReaderCsvImpl implements QuestionReader {

    private final ReaderProperties properties;

    public QuestionReaderCsvImpl(ReaderProperties properties) {
        this.properties = properties;
    }

    @Override
    public List<Question> readQuestions() {
        Resource resource = new ClassPathResource(properties.getFilePathLocale());
        if (!resource.exists()) {
            resource = new ClassPathResource(properties.getFilePathDefault());
        }
        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            CsvToBean<Question> csvToBean = new CsvToBeanBuilder<Question>(reader)
                    .withType(Question.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (Exception e) {
            throw new ReaderException("Ошибка: Не удалось прочитать тесты");
        }
    }
}
