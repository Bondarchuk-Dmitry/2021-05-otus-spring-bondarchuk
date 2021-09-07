package ru.otus.lec5.service.translate;

public interface LocalizeService {
    String getLocalizeText(String key);
    String getLocalizeText(String key, Object[] objects);
}
