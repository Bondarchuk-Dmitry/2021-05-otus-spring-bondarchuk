package ru.otus.lec5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.lec5.service.question.QuestionService;

@ShellComponent
@RequiredArgsConstructor
public class ShellApplication {

    private final QuestionService questionService;

    @ShellMethod(value = "Start question", key = {"s", "start"})
    public void start() {
        questionService.start();
    }
}
