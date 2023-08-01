package com.gyankosh.controller;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    public MainScreen() {
        setTitle("Gyankosh");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the navigation bar.
        JPanel navBar = new JPanel();
        navBar.add(new JButton("Scriptures"));
        navBar.add(new JButton("Bookmarks"));
        navBar.add(new JButton("Forum"));
        add(navBar, BorderLayout.NORTH);

        // Create the main area.
        JTextArea mainArea = new JTextArea();
        add(new JScrollPane(mainArea), BorderLayout.CENTER);

        // Create the sidebar.
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, 600));
        sidebar.add(new JLabel("Daily Verse: ..."));
        add(sidebar, BorderLayout.EAST);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainScreen::new);
    }
}

