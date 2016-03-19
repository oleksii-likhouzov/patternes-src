package org.test.model.example.dao;

import org.test.model.example.model.Address;
import org.test.model.example.model.Person;

import java.util.List;

public interface AddressDao {
    void store(Address address);
    void remove(Address address);
    Address getAddress(Long id);
}
