package ru.otus.lec2.service.io;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleIO {

    public static void print(String header, String text) {
        System.out.printf("%s: %s\n", header, text);
    }

    public static String readInput(String header, InputStream in) {
        System.out.printf("%s: ", header);
        return new Scanner(in).nextLine();
    }

}
