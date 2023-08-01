package com.gyankosh.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.gyankosh.model.LoginDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainScreen extends JFrame {

    public MainScreen() {
        setTitle("GyanKosh");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the navigation bar.
        JPanel navBar = new JPanel();
        
        JButton viewScripturesButton = new JButton("View Scriptures");
        viewScripturesButton.addActionListener(e -> showScriptureOptions());
        navBar.add(viewScripturesButton);

        JButton loginRegisterButton = new JButton("Login/Register");
        loginRegisterButton.addActionListener(e -> new LoginDialog(this).setVisible(true));
        navBar.add(loginRegisterButton);
        
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

    private void showScriptureOptions() {
        String[] options = { "Ramayan", "Gita" };
        int response = JOptionPane.showOptionDialog(this, "Which scripture would you like to view?",
                "Select Scripture", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if(response == 0) {
            openPDF("path_to_ramayan_pdf_file");
        } else if(response == 1) {
            openPDF("path_to_gita_pdf_file");
        }
    }

    private void openPDF(String path) {
        try {
            PDDocument document = PDDocument.load(new File(path));
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page)
            { 
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                displayImage(bim);
            }           
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayImage(BufferedImage image) {
        JFrame frame = new JFrame("PDF Viewer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel(new ImageIcon(image));
        JScrollPane jsp = new JScrollPane(label);
        frame.getContentPane().add(jsp, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainScreen::new);
    }
}



