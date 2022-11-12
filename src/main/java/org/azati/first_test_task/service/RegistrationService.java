package org.azati.first_test_task.service;

import org.azati.first_test_task.entity.Person;
import org.azati.first_test_task.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationService {
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonService personService, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(Role.ROLE_USER);
        personService.insert(person);
    }
}
