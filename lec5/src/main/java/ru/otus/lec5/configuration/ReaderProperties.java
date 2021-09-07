package ru.otus.lec5.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "question")
@Data
public class ReaderProperties {
    private String fileName;
    private String locale;

    public String getFilePathLocale() {
        if (!locale.equals("")) {
            return String.format("%s_%s.csv", fileName, locale);
        }
        return getFilePathDefault();
    }

    public String getFilePathDefault() {
        return String.format("%s.csv", fileName);
    }
}
