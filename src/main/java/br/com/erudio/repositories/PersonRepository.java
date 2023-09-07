package br.com.erudio.repositories;

import br.com.erudio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {


    Optional<Person> findByEmail(String email);
}