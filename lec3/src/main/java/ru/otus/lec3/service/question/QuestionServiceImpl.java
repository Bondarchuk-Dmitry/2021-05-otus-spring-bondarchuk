package ru.otus.lec3.service.question;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.lec3.model.Person;
import ru.otus.lec3.model.Question;
import ru.otus.lec3.model.Status;
import ru.otus.lec3.service.exception.QuestionException;
import ru.otus.lec3.service.facade.LocalizeIO;
import ru.otus.lec3.service.io.IOService;
import ru.otus.lec3.service.person.PersonService;
import ru.otus.lec3.service.reader.QuestionReader;
import ru.otus.lec3.service.translate.LocalizeService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionReader reader;
    private final LocalizeService localize;
    private final IOService console;
    private final LocalizeIO localizeOut;
    private final PersonService personService;
    private final int percentAnswer;
    private final int questionCount;

    public QuestionServiceImpl(QuestionReader reader,
                               LocalizeService localize,
                               IOService console,
                               LocalizeIO localizeOut,
                               PersonService personService,
                               @Value("${question.correctAnswerPercent}") int percentAnswer,
                               @Value("${question.count}") int questionCount) {
        this.reader = reader;
        this.localize = localize;
        this.console = console;
        this.localizeOut = localizeOut;
        this.personService = personService;
        this.percentAnswer = percentAnswer;
        this.questionCount = questionCount;
    }

    @Override
    public void start() {
        int correctAnswerCount = 0;
        List<Question> questions = reader.readQuestions();
        if (!checkQuestions(questions)) {
            throw new QuestionException("Ошибка: Тестирование не возможно, так как не достаточно тестов в базе");
        }
        Person person = personService.askPersonInfo();
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

    private boolean checkQuestions(List<Question> questions) {
        return questions.size() >= questionCount;
    }

    private void askQuestion(Question question) {
        localizeOut.println("question", question.getQuestion());
    }

    private String getAnswerUser() {
        localizeOut.print("answer");
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
        localizeOut.print("result",
                person.getFirstName(),
                person.getLastName(),
                localize.getLocalizeText(String.format("status.%s", status.getName())),
                String.valueOf(percentCorrectAnswer));
    }
}
