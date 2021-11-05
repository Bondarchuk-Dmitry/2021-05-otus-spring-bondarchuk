package ru.otus.lec20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class Lec20Application {

    public static void main(String[] args) {
        SpringApplication.run(Lec20Application.class, args);
    }
}
