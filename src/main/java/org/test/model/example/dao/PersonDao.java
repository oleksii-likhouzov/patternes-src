package org.test.model.example.dao;


import org.test.model.example.model.Person;

public interface PersonDao {
    void store(Person person);

    Person getPerson(Long id);
}
