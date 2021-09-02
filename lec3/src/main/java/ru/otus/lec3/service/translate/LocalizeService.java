package ru.otus.lec3.service.translate;

public interface LocalizeService {
    String getLocalizeText(String key);
    String getLocalizeText(String key, Object[] objects);
}
