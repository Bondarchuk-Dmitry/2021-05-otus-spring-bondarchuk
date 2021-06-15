package ru.otus.lec2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.lec2.model.Question;
import ru.otus.lec2.service.exception.QuestionException;
import ru.otus.lec2.service.io.ConsoleIO;
import ru.otus.lec2.service.reader.QuestionReader;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionReader reader;

    @Value("${question.correctAnswerPercent}")
    private int percentAnswer;
    @Value("${question.count}")
    private int questionCount;

    public void startTesting() {
        int correctAnswerCount = 0;
        String firstName = ConsoleIO.readInput("input first name", System.in);
        String LastName = ConsoleIO.readInput("input last name", System.in);
        List<Question> questions = reader.readQuestions();
        if (questions.size() < questionCount) {
            throw new QuestionException("Тестирование не возможно, так как не достаточно тестов в базе");
        }
        for (int i = 0; i < questionCount; i++) {
            ConsoleIO.print("Question", questions.get(i).getQuestion() + "?");
            String answer = ConsoleIO.readInput("Answer", System.in);
            if (checkAnswer(answer, questions.get(i))) {
                correctAnswerCount++;
            }
        }
        int percentCorrectAnswer = (int) (1.00 * correctAnswerCount / questionCount * 100);
        ConsoleIO.print("Result",
                percentCorrectAnswer >= percentAnswer
                        ? textResult("complete", percentCorrectAnswer)
                        : textResult("failure", percentCorrectAnswer));
    }

    private boolean checkAnswer(String answer, Question question) {
        return answer.equalsIgnoreCase(question.getCorrectAnswer());
    }

    private String textResult (String status, int percent) {
        return "test " + status + ".You answered: " + percent + "%";
    }
}
