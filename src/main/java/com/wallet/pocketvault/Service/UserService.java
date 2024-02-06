package com.wallet.pocketvault.Service;

import com.wallet.pocketvault.Entity.User;
import com.wallet.pocketvault.Repository.UserDAO;

public class UserService {
    private final UserDAO userDao;
    public UserService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    public User registerUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        userDao.registerUser(user);
        return user;
    }

    public User authenticateUser(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
