package ru.otus.lec3.service.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lec3.model.Person;
import ru.otus.lec3.service.translate.LocalizeService;
import ru.otus.lec3.service.io.IOService;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final IOService io;
    private final LocalizeService msg;


    @Override
    public Person askPersonInfo () {
        return new Person(
                this.askFirstName(),
                this.askLastName()
        );
    }

    private String askFirstName() {
        io.print(msg.getLocalizeText("person.firstName"));
        return io.read();
    }

    private String askLastName() {
        io.print(msg.getLocalizeText("person.lastName"));
        return io.read();
    }
}
