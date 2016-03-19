package org.test.model.example.service;


import org.test.model.example.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {
    Long addPerson(Person person) throws SQLException;
    void removePerson(Person person);
    Person getPerson(Long id);
    List<Person> getAllPerson();
}
