package org.test.model.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by stalker on 16.03.16.
 */
public class DaoFactoryImpl implements  DaoFactory{
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public PersonDao getPersonDao(Connection connection) {
        return null;
    }

    @Override
    public AddressDao getAddressDao(Connection connection) {
        return null;
    }

    @Override
    public UidDao getUidDao(Connection connection) {
        return null;
    }
}
