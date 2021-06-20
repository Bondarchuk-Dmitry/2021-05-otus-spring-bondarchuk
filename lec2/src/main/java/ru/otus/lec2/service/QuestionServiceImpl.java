package ru.otus.lec2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.lec2.model.Person;
import ru.otus.lec2.model.Question;
import ru.otus.lec2.model.Status;
import ru.otus.lec2.service.io.IOService;
import ru.otus.lec2.service.reader.QuestionReader;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionReader reader;
    private final IOService console;
    private final PersonService personService;
    private final int percentAnswer;
    private final int questionCount;

    public QuestionServiceImpl(QuestionReader reader,
                               IOService console,
                               PersonService personService,
                               @Value("${question.correctAnswerPercent}") int percentAnswer,
                               @Value("${question.count}") int questionCount) {
        this.reader = reader;
        this.console = console;
        this.personService = personService;
        this.percentAnswer = percentAnswer;
        this.questionCount = questionCount;
    }

    @Override
    public void start() {
        Person person = personService.askPersonInfo();
        int correctAnswerCount = 0;
        List<Question> questions = reader.readQuestions();
        checkingQuestions(questions);
        for (int i = 0; i < questionCount; i++) {
            askQuestion(questions.get(i));
            String answer = getAnswerUser();
            if (checkAnswer(answer, questions.get(i))) {
                correctAnswerCount++;
            }
        }
        int percentCorrectAnswer = getPercentCorrectAnswer(correctAnswerCount);
        Status status = getResultStatus(percentCorrectAnswer);
        printResult(person, status, percentCorrectAnswer);
    }

    private void checkingQuestions(List<Question> questions) {
        if (questions.size() < questionCount) {
            console.println("ERROR: Тестирование не возможно, так как не достаточно тестов в базе");
        }
    }

    private void askQuestion(Question question) {
        console.printf("Question: %s?\n", question.getQuestion());
    }

    private String getAnswerUser() {
        console.print("Answer: ");
        return console.read();
    }

    private boolean checkAnswer(String answer, Question question) {
        return answer.equalsIgnoreCase(question.getCorrectAnswer());
    }

    private int getPercentCorrectAnswer(int correctAnswerCount) {
        return (int) (1.00 * correctAnswerCount / questionCount * 100);
    }

    private Status getResultStatus(int percentCorrectAnswer) {
        return percentCorrectAnswer >= percentAnswer ? Status.COMPLETE : Status.FAILURE;
    }

    private void printResult(Person person, Status status, int percentCorrectAnswer) {
        console.printf("%s %s, your result: \n", person.getFirstName(), person.getLastName());
        console.printf("Test %s. You answered: %s%%", status.getName(), percentCorrectAnswer);
    }
}
