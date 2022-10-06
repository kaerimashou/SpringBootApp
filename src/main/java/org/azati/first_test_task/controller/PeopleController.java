package org.azati.first_test_task.controller;

import org.azati.first_test_task.entity.Book;
import org.azati.first_test_task.entity.Person;
import org.azati.first_test_task.service.PersonService;
import org.azati.first_test_task.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String index(Model model){
        List<Person> people = personService.getAll();
        model.addAttribute("people", people);
        return "people/peopleList";
    }

    @GetMapping("/{id}")
    public String personInfo(@PathVariable String id, Model model){
        Long personId = Long.parseLong(id);
        Person person = personService.getById(personId);
        model.addAttribute("person", person);
        List<Book> borrowedBooks = personService.getPersonBorrowedBooks(person);
        model.addAttribute("books",borrowedBooks);
        return "people/personInfo";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("title", "New person");
        return "people/personEdit";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable String id, Model model){
        Long personId = Long.parseLong(id);
        Person person = personService.getById(personId);
        model.addAttribute("person", person);
        model.addAttribute("title", "Edit of " + person.getName());
        return "people/personEdit";
    }

    @PostMapping
    public String updatePersonList(@ModelAttribute("person") @Valid Person person,
                                 BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "people/personEdit";
        }
        personService.insert(person);
        if (person.getId() != null){
            return "redirect:/people/" + person.getId();
        }
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable String id){
        long personId = Long.parseLong(id);
        personService.delete(personId);
        return "redirect:/people";
    }
}
