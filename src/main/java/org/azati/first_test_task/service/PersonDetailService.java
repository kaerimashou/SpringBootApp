package org.azati.first_test_task.service;

import org.azati.first_test_task.entity.Person;
import org.azati.first_test_task.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {
    private final PersonService personService;

    @Autowired
    public PersonDetailService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personService.getByUsername(username);

        if(!person.isPresent())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }
}
