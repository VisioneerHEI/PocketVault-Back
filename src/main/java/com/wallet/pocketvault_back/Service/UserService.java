package com.wallet.pocketvault_back.Service;

import com.wallet.pocketvault_back.Entity.User;
import com.wallet.pocketvault_back.Repository.UserDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User saveUser(User toSave) {
        try {
            this.userDAO.save(toSave);

            return toSave;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when saving the User.");
        }
    }

    public User updateUser(User toUpdate) {
        try {
            this.userDAO.update(toUpdate);
            return toUpdate;
        } catch (SQLException e) {
            throw new RuntimeException("there was an error when updating the user");
        }
    }

    public List<User> findAll() {
        try {
            return userDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching all users");
        }
    }

    public Optional<User> findById(int id) {
        try {
            return userDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching user with id : " + id);
        }
    }

}
