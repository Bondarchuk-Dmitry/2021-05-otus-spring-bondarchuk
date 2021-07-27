package ru.otus.lec3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.lec3.service.question.QuestionService;

@SpringBootApplication
public class Lec3Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Lec3Application.class, args);
		QuestionService questionService = ctx.getBean(QuestionService.class);
		questionService.start();
	}

}
