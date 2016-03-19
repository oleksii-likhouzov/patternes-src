package org.test.model.example.dao;


import org.test.model.example.model.Uid;

public interface UidDao {
    void store(Uid uid);

    Uid getLastUid(String name);
}
