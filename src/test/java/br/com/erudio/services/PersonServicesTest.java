package br.com.erudio.services;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServices services;

    private Person person0;


    @BeforeEach
    public void setup() {
        person0 = new Person(
                "Flavio",
                "Monteiro",
                "monteiro@gmail.com",
                "Recife",
                "Male");
    }

    @DisplayName("Junit test for Given Person Object When Save Person then Return Person Object")
    @Test
    void testGivenPersonObject_WhenSavePerson_thenReturnPersonObject() {

        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person0)).willReturn(person0);

        Person savedPerson = services.create(person0);

        assertNotNull(savedPerson);
        assertEquals("Monteiro", savedPerson.getLastName());
        assertEquals("Flavio", savedPerson.getFirstName());
    }

    @DisplayName("Junit test for Given Existing Email When Save Person then Return Person Object")
    @Test
    void testGivenExistingEmail_WhenSavePerson_thenReturnPersonObject() {

        given(repository.findByEmail(anyString())).willReturn(Optional.of(person0));

        assertThrows(ResourceNotFoundException.class, () -> {
            services.create(person0);
        });
        verify(repository, never()).save(any(Person.class));
    }

    @DisplayName("Junit test for Given Existing Email When Save Person then Return Person Object")
    @Test
    void testGivenPersonsList_WhenFindAllPersons_thenReturnPersonsList() {
        Person person1 = new Person("Claudio", "Costa", "costa@gmail.com", "Caruaru", "Male");
        given(repository.findAll()).willReturn(List.of(person0, person1));

        List<Person> personList = services.findAll();

        assertNotNull(personList);
        assertEquals(2, personList.size());
    }

    @DisplayName("Junit test for Given Empty Persons List When Find AllPersons then Return Empty Persons List")
    @Test
    void testGivenEmptyPersonsList_WhenFindAllPersons_thenReturnEmptyPersonsList() {
        given(repository.findAll()).willReturn(Collections.emptyList());

        List<Person> personList = services.findAll();

        assertNotNull(personList.isEmpty());
        assertEquals(0, personList.size());
    }

    @DisplayName("Junit test for Given Person Id When Find By Id then Return Person Object")
    @Test
    void testGivenPersonId_WhenFindById_thenReturnPersonObject() {

        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        Person savedPerson = services.findById(1L);

        assertNotNull(savedPerson);
        assertEquals("Monteiro", savedPerson.getLastName());
        assertEquals("Flavio", savedPerson.getFirstName());
    }

    @DisplayName("Junit test for Given Person Object When Update Person then Return Update Person Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatePersonObject() {

        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        person0.setFirstName("Mateus");
        person0.setEmail("mateus@gmail.com");

        given(repository.save(person0)).willReturn(person0);

        Person updatedPerson = services.update(person0);

        assertNotNull(updatedPerson);
        assertEquals("Mateus", updatedPerson.getFirstName());
        assertEquals("mateus@gmail.com", updatedPerson.getEmail());
    }

    @DisplayName("Junit test for Given PersonId When Delete Person then Do Nothing")
    @Test
    void testGivenPersonId_WhenDeletePerson_thenDoNothing() {

        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));
        willDoNothing().given(repository).delete(person0);

        services.delete(person0.getId());

        verify(repository, times(1)).delete(person0);
    }
}
