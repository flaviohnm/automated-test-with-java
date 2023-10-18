package br.com.erudio.controllers;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PersonServices service;

    private Person person;

    @BeforeEach
    public void setup() {
        person = new Person(
                "Flavio",
                "Monteiro",
                "monteiro@gmail.com",
                "Recife",
                "Male");
    }

    @DisplayName("JUnit test for Given Person Object When Create Person Then Return Saved Person")
    @Test
    void testGivenPersonObject_WhenCreatePerson_ThenReturnSavedPerson() throws Exception {

        given(service.create(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
        ;
    }

    @DisplayName("JUnit test for Given List Of Persons When FindAll Persons Then Return Persons List")
    @Test
    void testGivenListOfPersons_WhenFindAllPersons_ThenReturnPersonsList() throws Exception {

        List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(new Person(
                "Mateus",
                "Monteiro",
                "matues@gmail.com",
                "Recife",
                "Male"));

        given(service.findAll()).willReturn(persons);

        ResultActions response = mockMvc.perform(get("/person"));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(persons.size())));
    }

    @DisplayName("JUnit test for Given PersonId When Find By Id Then Return Person Object")
    @Test
    void testGivenPersonId_WhenFindById_ThenReturnPersonObject() throws Exception {

        long personId = 1L;
        given(service.findById(personId)).willReturn(person);

        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }

    @DisplayName("JUnit test for Given Invalid PersonId When Find By Id Then Return Not Found")
    @Test
    void testGivenInvalidPersonId_WhenFindById_ThenReturnNotFound() throws Exception {

        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);

        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @DisplayName("JUnit test for Given Updated Person When Update Then Return Updated Person Object")
    @Test
    void testGivenUpdatedPerson_WhenUpdate_ThenReturnUpdatedPersonObject() throws Exception {
        Person updatedPerson = new Person(
                "Mateus",
                "Monteiro",
                "matues@gmail.com",
                "Recife",
                "Male");

        long personId = 1L;
        given(service.findById(personId)).willReturn(person);
        given(service.update(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));


        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedPerson)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
    }

    @DisplayName("JUnit test for Given Invalid PersonId When Find By Id Then Return Not Found")
    @Test
    void testGivenUnexistentPerson_WhenUpdate_ThenReturnNotFound() throws Exception {

        Person updatedPerson = new Person(
                "Mateus",
                "Monteiro",
                "matues@gmail.com",
                "Recife",
                "Male");

        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
        given(service.update(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(1));

        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedPerson)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }


    @DisplayName("JUnit test for Given PersonId When Delete Then Return No Content")
    @Test
    void testGivenPersonId_WhenDelete_ThenReturnNoContent() throws Exception {


        long personId = 1L;

        willDoNothing().given(service).delete(personId);

        ResultActions response = mockMvc.perform(delete("/person/{id}", personId));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }
}
