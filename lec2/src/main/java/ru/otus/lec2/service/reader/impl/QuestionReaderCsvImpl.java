package ru.otus.lec2.service.reader.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.lec2.model.Question;
import ru.otus.lec2.service.exception.ReaderException;
import ru.otus.lec2.service.reader.QuestionReader;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class QuestionReaderCsvImpl implements QuestionReader {


    private final Resource resource;

    public QuestionReaderCsvImpl(@Value("classpath:questions.csv") Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Question> readQuestions() {
        try (Reader reader = new InputStreamReader(resource.getInputStream());) {

            CsvToBean<Question> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Question.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (Exception e) {
            throw new ReaderException("Не удалось прочитать тесты");
        }
    }
}
