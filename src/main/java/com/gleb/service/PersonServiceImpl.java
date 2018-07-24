package com.gleb.service;

import com.gleb.domain.elastic.PersonSearch;
import com.gleb.domain.mongo.Person;
import com.gleb.repository.elastic.PersonElasticRepository;
import com.gleb.repository.mongo.PersonRepository;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonElasticRepository personElasticRepository;

    @Override
    public Person save(Person person) throws Exception {
        LOGGER.debug("Creating new person {}.", person);

        Validate.notNull(person, "Person must not be null.");

        try {
            Person saved = personRepository.save(person);

            personElasticRepository.save(new PersonSearch(saved.getId(), saved.getName(), saved.getEmail()));

            LOGGER.debug("Person was created {}.", saved);

            return saved;
        } catch (Exception e) {
            String message = String.format("Error appeared while creating new person %s.", person);
            LOGGER.error(message, e);
            throw new Exception(message, e);
        }
    }

    @Override
    public PersonSearch getById(UUID id) throws Exception {
        LOGGER.debug("Search person in elastic by id {}.", id);

        Validate.notNull(id, "Id must not be null.");

        try {
            Optional<PersonSearch> optional = personElasticRepository.findById(id);

            if (optional.isPresent()) {
                LOGGER.debug("Person {} was found by id {}.", optional.get(), id);

                return optional.get();
            }

            LOGGER.debug("Person was not found by id {}.", id);

            return null;
        } catch (Exception e) {
            String message = String.format("Error appeared while finding person by id %s.", id);
            LOGGER.error(message, e);
            throw new Exception(message, e);
        }
    }

    @Override
    public List<Person> getAll() throws Exception {
        LOGGER.debug("Finding all persons in mongo.");

        try {

            List<Person> persons = personRepository.findAll();

            LOGGER.debug("Was retrieved persons, size {}", persons.size());

            return persons;

        } catch (Exception e) {
            String message = "Error appeared while finding all persons.";
            LOGGER.error(message, e);
            throw new Exception(message, e);
        }
    }
}
