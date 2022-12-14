package org.azati.first_test_task.service;

import org.azati.first_test_task.entity.Book;
import org.azati.first_test_task.entity.Person;
import org.azati.first_test_task.repository.BookRepository;
import org.azati.first_test_task.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public void insert(Person person) {
        personRepository.saveAndFlush(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getByEmail(String email) {
        return personRepository.findFirstByEmail(email);
    }
    public Optional<Person> getByUsername(String username) {
        return personRepository.findFirstByUserName(username);
    }
}
