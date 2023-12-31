package br.com.erudio.repositories;

import br.com.erudio.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    private Person person0;

    @BeforeEach
    public void setup(){
        person0 = new Person(
                "Flavio",
                "Monteiro",
                "monteiro@gmail.com",
                "Recife",
                "Male");
    }

    @Test
    @DisplayName("Given Person Object when Save then Return Saved Person")
    void testGivenPersonObject_whenSave_thenReturnSavedPerson() {

        //When
        Person savedPerson = repository.save(person0);

        //Then
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);

    }

    @Test
    @DisplayName("Given Person List when FindAll then Return Person List")
    void testGivenPersonList_whenFindAll_thenReturnPersonList() {

        //Given
        Person person1 = new Person("Claudio", "Costa", "costa@gmail.com", "Caruaru", "Male");
        repository.save(person0);
        repository.save(person1);

        //When
        List<Person> personList = repository.findAll();

        //Then
        assertNotNull(personList);
        assertEquals(2,personList.size());

    }

    @Test
    @DisplayName("Given Person Object when FindByID then Return Person Object")
    void testGivenPersonObject_whenFindByID_thenReturnPersonObject() {

        //Given
        repository.save(person0);

        //When
        Person savedPerson = repository.findById(person0.getId()).get();

        //Then
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());

    }

    @Test
    @DisplayName("Given Person Object when FindByEmail then Return Person Object")
    void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject() {

        //Given
        repository.save(person0);

        //When
        Person savedPerson = repository.findByEmail(person0.getEmail()).get();

        //Then
        assertNotNull(savedPerson);
        assertEquals(person0.getEmail(), savedPerson.getEmail());

    }

    @Test
    @DisplayName("Given Person Object when Update Person then Return Updated Person Object")
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPersonObject() {

        //Given
        repository.save(person0);

        //When
        Person savedPerson = repository.findById(person0.getId()).get();
        savedPerson.setFirstName("Heleno");
        savedPerson.setEmail("heleno@gmail.com");

        Person updatedPerson = repository.save(savedPerson);

        //Then
        assertNotNull(updatedPerson);
        assertEquals("Heleno", updatedPerson.getFirstName());
        assertEquals("heleno@gmail.com", updatedPerson.getEmail());

    }


    @Test
    @DisplayName("Given Person Object when Delete then Remove Person")
    void testGivenPersonObject_whenDelete_thenRemovePerson() {

        //Given
        repository.save(person0);

        //When
        repository.deleteById(person0.getId());

        Optional<Person> personOptional = repository.findById(person0.getId());

        //Then
        assertTrue(personOptional.isEmpty());

    }

    @Test
    @DisplayName("JUnit test for Given FirstName And LastName when FindJPQL then Return Person Object")
    void testGivenFirstNameAndLastName_whenFindJPQL_thenReturnPersonObject() {

        //Given
        repository.save(person0);

        String firstName = "Flavio";
        String lastName = "Monteiro";
        //When
        Person savedPerson = repository.findByJPQL(firstName, lastName);

        //Then
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());

    }

    @Test
    @DisplayName("JUnit test for Given FirstName And LastName when FindJPQL Named Parameters then Return Person Object")
    void testGivenFirstNameAndLastName_whenFindJPQLNamedParameters_thenReturnPersonObject() {

        //Given
        repository.save(person0);

        String firstName = "Flavio";
        String lastName = "Monteiro";
        //When
        Person savedPerson = repository.findByJPQLNamedParameters(firstName, lastName);

        //Then
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());

    }

    @Test
    @DisplayName("JUnit test for Given FirstName And LastName when Find Native SQL then Return Person Object")
    void testGivenFirstNameAndLastName_whenFindNativeSQL_thenReturnPersonObject() {

        //Given
        repository.save(person0);

        String firstName = "Flavio";
        String lastName = "Monteiro";
        //When
        Person savedPerson = repository.findByNativeSQL(firstName, lastName);

        //Then
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());

    }

    @Test
    @DisplayName("JUnit test for Given FirstName And LastName when Find Native SQL With Named Parameters then Return Person Object")
    void testGivenFirstNameAndLastName_whenFindNativeSQLWithNamedParameters_thenReturnPersonObject() {

        //Given
        repository.save(person0);

        String firstName = "Flavio";
        String lastName = "Monteiro";
        //When
        Person savedPerson = repository.findByNativeSQLWithNamedParameters(firstName, lastName);

        //Then
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());

    }

}
