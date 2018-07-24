package com.gleb.web;

import com.gleb.domain.elastic.PersonSearch;
import com.gleb.domain.mongo.Person;
import com.gleb.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @PostMapping
    public Person create(@RequestBody Person person) throws Exception {
        LOGGER.debug("Create new person {}.", person);

        person.setId(UUID.randomUUID());
        return personService.save(person);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public PersonSearch getById(@NonNull @PathVariable("id") UUID id) throws Exception {
        LOGGER.debug("Retrieve person by id {}.", id);

        return personService.getById(id);
    }

    @GetMapping
    @RequestMapping("/all")
    public List<Person> getAll() throws Exception {
        LOGGER.debug("Retrieve all persons.");

        return personService.getAll();
    }
}
