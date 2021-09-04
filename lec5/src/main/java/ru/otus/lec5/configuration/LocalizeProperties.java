package ru.otus.lec5.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
@ConfigurationProperties(prefix = "question")
public class LocalizeProperties {

    @Setter
    private String locale;

    private Locale language;

    @PostConstruct
    void init() {
        language = Locale.forLanguageTag(locale);
    }

    public Locale getLanguage(){
        return language;
    }
}
