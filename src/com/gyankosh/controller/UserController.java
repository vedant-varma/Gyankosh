package com.gyankosh.controller;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // methods to handle user input and call the appropriate userService methods
}

