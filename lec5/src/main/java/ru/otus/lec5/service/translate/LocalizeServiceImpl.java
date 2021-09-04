package ru.otus.lec5.service.translate;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.lec5.configuration.LocalizeProperties;

@Service
@RequiredArgsConstructor
public class LocalizeServiceImpl implements LocalizeService {

    private final LocalizeProperties localize;
    private final MessageSource msg;


    @Override
    public String getLocalizeText(String key) {
        return getLocalizeText(key, new Object());
    }

    @Override
    public String getLocalizeText(String key, Object... objects) {
        return msg.getMessage(key, objects, localize.getLanguage());
    }

}
