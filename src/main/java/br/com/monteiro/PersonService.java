package br.com.monteiro;

import br.com.monteiro.model.Person;
import br.com.monteiro.service.IPersonService;

public class PersonService implements IPersonService {
    @Override
    public Person createPerson(Person person) {

        return person;
    }
}
