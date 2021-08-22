package ru.otus.lec3.service.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec3.model.Person;
import ru.otus.lec3.service.facade.LocalizeIO;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final LocalizeIO io;

    @Override
    public Person askPersonInfo () {
        return new Person(
                this.askFirstName(),
                this.askLastName()
        );
    }

    private String askFirstName() {
        io.print("person.firstName");
        return io.read();
    }

    private String askLastName() {
        io.print("person.lastName");
        return io.read();
    }
}
