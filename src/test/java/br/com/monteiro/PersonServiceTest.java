package br.com.monteiro;

import br.com.monteiro.model.Person;
import br.com.monteiro.service.IPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonServiceTest {

    Person person;

    @BeforeEach
    void setup() {
        person = new Person(
                "Keith",
                "Moon",
                "Wembley - UK",
                "Male",
                "kmoon@monteiro.com.br"
        );
    }

    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {

        //Given
        IPersonService service = new PersonService();

        //When
        Person actual = service.createPerson(person);

        //Then
        assertNotNull(actual, () -> "The createPerson() should not have returned null!");

    }


    @DisplayName("When Create a Person with Success Should Contains FirstName in Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsFirstNameInReturnPersonObject() {

        //Given
        IPersonService service = new PersonService();
        
        //When
        Person actual = service.createPerson(person);

        //Then
        assertEquals(person.getFirstName(), actual.getFirstName(), () -> "The FirstName is different!");

    }
}
