package ru.otus.lec1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.lec1.service.QuestionService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        service.getQuestions().forEach(
                question -> System.out.printf("%s?\n",question.getQuestion()));
    }
}
