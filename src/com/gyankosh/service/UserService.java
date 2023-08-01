package com.gyankosh.service;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String username, String password) {
        // logic to validate user credentials and log the user in
    }

    public void register(String username, String password, String email) {
        // logic to register a new user
    }

    // any other methods you need
}

