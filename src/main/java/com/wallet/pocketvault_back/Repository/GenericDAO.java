package com.wallet.pocketvault_back.Repository;

import com.wallet.pocketvault_back.Entity.Currency;
import com.wallet.pocketvault_back.Entity.Transaction;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Getter
public abstract class GenericDAO <T>{
    private final Connection connection;

    protected GenericDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract void save(T toSave) throws SQLException;

    public abstract void update(T toUpdate) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract Optional<T> findById(int id) throws SQLException;
}
