package ru.otus.lec3.service.facade;

public interface LocalizeIO {
    void print(String msgKey);
    void print(String msgKey,  Object... args);
    void println(String msgKey);
    void println(String msgKey, Object... args);
}
