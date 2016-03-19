package org.test.model.example.service;

import org.test.example.singleton.Context;
import org.test.model.example.dao.DaoFactoryImpl;
import org.test.model.example.dao.UidDao;
import org.test.model.example.model.Uid;

import java.sql.SQLException;

public class UidServiceImpl implements UidService {
    @Override
    public Long getNextUid(String name) throws SQLException {
        UidDao uidDao = new DaoFactoryImpl().getUidDao(Context.getInstance().getConnection());
        Uid uid = uidDao.getLastUid(name);
        uid.setId(uid.getId() + 1L);
        uidDao.store(uid);
        return uid.getId();
    }
}
