package br.com.erudio.repositories;

import br.com.erudio.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    @DisplayName("Given Person Object when Save then Return Saved Person")
    void testGivenPersonObject_whenSave_thenReturnSavedPerson(){

        //Given
        Person person0 = new Person("Flavio", "Monteiro","monteiro@gmail.com","Recife", "Male");

        //When
        Person savedPerson = repository.save(person0);

        //Then
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);

    }
}
