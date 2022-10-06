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
    private final BookRepository bookRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public Person getById(Long id){
        return personRepository.findById(id).orElse(null);
    }

    public void insert(Person person){
        personRepository.saveAndFlush(person);
    }

    public List<Book> getPersonBorrowedBooks(Person borrower){
        return bookRepository.findAllByBorrower(borrower);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getByEmail(String email) {
        return personRepository.findFirstByEmail(email);
    }
}
