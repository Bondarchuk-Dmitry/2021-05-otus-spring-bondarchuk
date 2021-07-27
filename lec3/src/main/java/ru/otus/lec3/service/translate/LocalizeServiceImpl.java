package ru.otus.lec3.service.translate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizeServiceImpl implements LocalizeService {

    private final String locale;
    private final MessageSource msg;

    public LocalizeServiceImpl(@Value("${question.locale}") String locale,
                               MessageSource msg) {
        this.locale = locale;
        this.msg = msg;
    }

    @Override
    public String getLocalizeText(String key) {
        return getLocalizeText(key, null);
    }

    @Override
    public String getLocalizeText(String key, Object[] objects) {
        return msg.getMessage(key, objects, Locale.forLanguageTag(locale));
    }

}
