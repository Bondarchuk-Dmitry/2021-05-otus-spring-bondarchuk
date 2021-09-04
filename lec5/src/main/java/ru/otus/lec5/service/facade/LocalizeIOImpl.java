package ru.otus.lec5.service.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.lec5.service.io.IOService;
import ru.otus.lec5.service.translate.LocalizeService;

@Component
@RequiredArgsConstructor
public class LocalizeIOImpl implements LocalizeIO {

    private final IOService io;
    private final LocalizeService localize;

    @Override
    public void println(String msgKey) {
        io.println(localize.getLocalizeText(msgKey));
    }

    @Override
    public void print(String msgKey) {
        io.print(localize.getLocalizeText(msgKey));
    }

    @Override
    public void print(String msgKey, Object... args) {
        io.print(localize.getLocalizeText(msgKey, args));
    }

    @Override
    public void println(String msgKey, Object... args) {
        io.println(localize.getLocalizeText(msgKey, args));
    }

    @Override
    public String read() {
        return io.read();
    }

    @Override
    public String getLocalizeText(String msgKey) {
        return localize.getLocalizeText(msgKey);
    }
}
