package com.gyankosh.dao;

public interface UserDao {
    User getUserByUsername(String username);
    void registerUser(User user);
    // any other methods you need
}

