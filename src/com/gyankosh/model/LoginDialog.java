package com.gyankosh.model;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginDialog(JFrame parent) {
        super(parent, "Login or Register", true);
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> register());
        add(registerButton);
    }

    private void login() {
        // Call the UserService to log in
    }

    private void register() {
        // Call the UserService to register
    }
}

