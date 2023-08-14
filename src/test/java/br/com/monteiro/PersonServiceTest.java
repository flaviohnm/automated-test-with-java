package br.com.monteiro;

import br.com.monteiro.model.Person;
import br.com.monteiro.service.IPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    IPersonService service;
    Person person;

    @BeforeEach
    void setup() {

        service = new PersonService();
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

        //When
        Person actual = service.createPerson(person);

        //Then
        assertNotNull(actual, () -> "The createPerson() should not have returned null!");

    }


    @DisplayName("When Create a Person with Success Should Contains Valid Fields in Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsValidFieldsInReturnPersonObject() {

        //Given

        //When
        Person actual = service.createPerson(person);

        //Then
        assertNotNull(person.getId(), () -> "Person ID is Missing!");
        assertEquals(person.getFirstName(), actual.getFirstName(), () -> "The FirstName is Incorrect!");
        assertEquals(person.getLastName(), actual.getLastName(), () -> "The LastName is Incorrect!");
        assertEquals(person.getEmail(), actual.getEmail(), () -> "The Email is Incorrect!");
        assertEquals(person.getAddress(), actual.getAddress(), () -> "The Address is Incorrect!");
        assertEquals(person.getGender(), actual.getGender(), () -> "The Gender is Incorrect!");

    }

    @DisplayName("When Create a Person with null e-mail Should throw Exception")
    @Test
    void testCreatePerson_WithNullEmail_ShouldThrowIllegalArgumentException() {

        //Given

        //When
        Person actual = service.createPerson(person);
        person.setEmail(null);

        var expectedMessage = "The Person e-Mail is null or empty!";

        //Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.createPerson(person),
                () -> "Empty e-Mail should have cause an IllegalArgumentException!");

        assertEquals(
                expectedMessage,
                exception.getMessage(),
                ()-> "Exception error message is incorrect!");
    }

}
