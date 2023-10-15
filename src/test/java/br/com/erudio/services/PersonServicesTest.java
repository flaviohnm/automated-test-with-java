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
}
