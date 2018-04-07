package person;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonRegistry {

    private List<String> persons = new ArrayList<>();

    public void register(String message) {
        persons.add(message);
    }

    public List<String> getPersons() {
        return persons;
    }
}