package ru.otus.lec5.service.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleIOService implements IOService {

    private final PrintStream out;
    private final Scanner sc;

    public ConsoleIOService(@Value("#{ T(java.lang.System).in}") InputStream in,
                            @Value("#{ T(java.lang.System).out}") PrintStream out) {
        this.out = out;
        this.sc = new Scanner(in);
    }

    @Override
    public void print(String text) {
        out.print(text);
    }

    @Override
    public void println(String text) {
        out.println(text);
    }

    @Override
    public void printf(String template, Object... args) {
        out.printf(template, args);
    }

    @Override
    public String read() {
        return sc.nextLine();
    }

}
