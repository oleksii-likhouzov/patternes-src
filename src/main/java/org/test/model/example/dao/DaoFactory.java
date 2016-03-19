package org.test.model.example.dao;


import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {
    Connection getConnection() throws SQLException;
    PersonDao getPersonDao(Connection connection);
    AddressDao getAddressDao(Connection connection);
    UidDao getUidDao(Connection connection);
}
