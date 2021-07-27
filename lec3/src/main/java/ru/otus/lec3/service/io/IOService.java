package ru.otus.lec3.service.io;

public interface IOService {
    void println(String msg);

    void print(String msg);

    void printf(String template, Object... args);

    String read();
}
