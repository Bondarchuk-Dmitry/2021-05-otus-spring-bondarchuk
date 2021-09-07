package ru.otus.lec5.translate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lec5.configuration.LocalizeProperties;
import ru.otus.lec5.service.translate.LocalizeService;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LocalizeServiceTest {

    @Autowired
    private LocalizeService localizeService;

    @MockBean
    private LocalizeProperties localizeProperties;

    @Test
    @DisplayName("Локализация для English")
    public void whenGetLocalizeText_thenReturnStringEnLocale() {
        when(localizeProperties.getLanguage()).thenReturn(Locale.ENGLISH);
        assertEquals(localizeService.getLocalizeText("question"), "Question");
    }

    @Test
    @DisplayName("Локализация для Russian")
    public void whenGetLocalizeText_thenReturnStringRuLocale() {
        when(localizeProperties.getLanguage()).thenReturn(Locale.forLanguageTag("ru"));
        assertEquals(localizeService.getLocalizeText("question"), "Вопрос");
    }

}
