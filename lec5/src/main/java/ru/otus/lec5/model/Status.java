package ru.otus.lec5.model;

public enum Status {
    COMPLETE("complete"),
    FAILURE("failure");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
