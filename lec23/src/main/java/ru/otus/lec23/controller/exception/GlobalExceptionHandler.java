package ru.otus.lec23.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(
            HttpServletRequest request,
            Exception ex
    ) {
        return new ErrorMessage(
                request.getRequestURL().toString(),
                ex.getClass().toString(),
                ex.getMessage(),
                Arrays.stream(ex.getStackTrace())
                        .map(StackTraceElement::toString).collect(Collectors.toList())
        );
    }

    @AllArgsConstructor
    @Data
    static class ErrorMessage {
        private String url;
        private String exception;
        private String message;
        private List<String> trace;
    }

}
