package com.gleb.service;

import com.gleb.domain.elastic.PersonSearch;
import com.gleb.domain.mongo.Person;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    Person save(Person person) throws Exception;

    PersonSearch getById(UUID id) throws Exception;

    List<Person> getAll() throws Exception;
}
