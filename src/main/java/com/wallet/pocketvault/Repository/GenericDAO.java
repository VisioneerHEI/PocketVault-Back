package com.wallet.pocketvault.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class GenericDAO <T>{
    private final Connection connection;

    protected GenericDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract void insert(T toInsert) throws SQLException;

    public abstract void update(T toUpdate) throws SQLException;

    public  abstract  void delete(T toDelete) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract Optional<T> findById(int id) throws SQLException;
}
