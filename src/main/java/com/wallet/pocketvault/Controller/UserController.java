package com.wallet.pocketvault.Controller;

import com.wallet.pocketvault.Entity.User;
import com.wallet.pocketvault.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/authenticate")
    public User authenticateUser(@RequestParam String username, @RequestParam String password) {
        return userService.authenticateUser(username, password);
    }
}
