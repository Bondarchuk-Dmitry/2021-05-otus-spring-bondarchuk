package ru.otus.lec1.service;

import org.springframework.core.io.Resource;
import ru.otus.lec1.model.Question;
import ru.otus.lec1.service.reader.QuestionReader;

import java.util.List;

public class QuestionService {

 private final Resource resource;
 private final QuestionReader reader;

 public QuestionService(Resource resource, QuestionReader reader) {
  this.resource = resource;
  this.reader = reader;
 }

 public List<Question> getQuestions() {
  return this.reader.readQuestions(this.resource);
 }
}
