package org.azati.first_test_task.controller;

import org.azati.first_test_task.entity.Person;
import org.azati.first_test_task.service.RegistrationService;
import org.azati.first_test_task.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registration(@ModelAttribute("person") Person person) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult result) {
        personValidator.validate(person, result);
        if (result.hasErrors()) {
            return "auth/register";
        } else {
            registrationService.register(person);
            return "redirect:/auth/login";
        }
    }

}
