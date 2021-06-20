package ru.otus.lec2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec2.model.Person;
import ru.otus.lec2.service.io.IOService;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final IOService console;

    @Override
    public Person askPersonInfo () {
        return new Person(
                this.askFirstName(),
                this.askLastName()
        );
    }

    private String askFirstName() {
        console.print("Input first name: ");
        return console.read();
    }

    private String askLastName() {
        console.print("Input last name: ");
        return console.read();
    }
}
