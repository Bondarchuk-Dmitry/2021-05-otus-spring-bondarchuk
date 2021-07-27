package ru.otus.lec3.service.reader.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.lec3.model.Question;
import ru.otus.lec3.service.exception.ReaderException;
import ru.otus.lec3.service.reader.QuestionReader;
import ru.otus.lec3.service.translate.LocalizeService;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class QuestionReaderCsvImpl implements QuestionReader {


    private final Resource resource;
    private final LocalizeService msgService;

    public QuestionReaderCsvImpl(
            @Value("classpath:questions.csv") Resource resource,
            LocalizeService msgService) {
        this.resource = resource;
        this.msgService = msgService;
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
            throw new ReaderException(String.format("%s: %s",
                    msgService.getLocalizeText("error"),
                    msgService.getLocalizeText("error.parseCsv")));
        }
    }
}
