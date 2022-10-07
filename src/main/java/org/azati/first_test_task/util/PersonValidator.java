package org.azati.first_test_task.util;

import org.azati.first_test_task.entity.Person;
import org.azati.first_test_task.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personService.getByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "person.unique.violation.email", "This email is already taken");
        }
    }
}
