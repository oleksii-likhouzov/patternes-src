package org.test.model.example.service;

import java.sql.SQLException;

public interface UidService {
    Long getNextUid(String name) throws SQLException;
}
