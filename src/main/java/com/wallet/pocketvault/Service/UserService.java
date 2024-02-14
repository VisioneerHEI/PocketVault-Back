package com.wallet.pocketvault.Service;

import com.wallet.pocketvault.Entity.User;
import com.wallet.pocketvault.Repository.UserDAO;

public class  UserService {
    private final UserDAO userDao;
    public UserService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    public void registerUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Username, password, and email cannot be empty");
        }
        userDao.registerUser(user);
    }

    public User authenticateUser(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty");
        }
        return userDao.authenticateUser(username, password);
    }
}
