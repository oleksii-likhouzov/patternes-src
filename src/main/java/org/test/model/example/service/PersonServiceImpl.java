package org.test.model.example.service;

import org.test.example.singleton.Context;
import org.test.model.example.dao.AddressDao;
import org.test.model.example.dao.DaoFactoryImpl;
import org.test.model.example.dao.PersonDao;
import org.test.model.example.model.Address;
import org.test.model.example.model.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonServiceImpl implements PersonService {
    private static final String UidPersonGeneratoreCode = "T_PERSON";
    private static final String UidAddressGeneratoreCode = "T_ADDRESS";

    @Override
    public Long addPerson(Person person) throws SQLException {
        try {
            UidService uidService = new UidServiceImpl();
            PersonDao personDao = new DaoFactoryImpl().getPersonDao(Context.getInstance().getConnection());
            person.setId(uidService.getNextUid(UidPersonGeneratoreCode));
            personDao.store(person);
            Address address = person.getAddress();
            if (address != null) {
                AddressDao addressDao = new DaoFactoryImpl().getAddressDao(Context.getInstance().getConnection());
                address.setId(uidService.getNextUid(UidAddressGeneratoreCode));
                addressDao.store(address);
            }
            Context.getInstance().getConnection().commit();
        } catch (Exception exception) {
            Context.getInstance().getConnection().rollback();
            exception.printStackTrace();
            throw new RuntimeException("addPerson", exception);
        }
        return person.getId();
    }

    @Override
    public void removePerson(Person person) {

    }

    @Override
    public Person getPerson(Long id) {
        return null;
    }

    @Override
    public List<Person> getAllPerson() {
        return null;
    }
}
