package com.company;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TitleGameScreen {

    JFrame frame;
    Container con;
    JPanel titleNamePanel, startButtonPanel;
    JLabel titleNameLabel;
    JButton startButton;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font buttonFont = new Font("Times New Roman", Font.PLAIN, 45);

    public static void main(String[] args) {
        new TitleGameScreen();
    }

    public TitleGameScreen() {
        System.setProperty("sun.java2d.opengl","True");

        frame = new JFrame("Hola");

        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);

        con = frame.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(50, 100, 1180, 150);
        titleNamePanel.setBackground(Color.BLACK);

        titleNameLabel = new JLabel("GILGAMESH DEU ABSOLUT");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(540, 400, 200, 100);
        startButtonPanel.setBackground(Color.BLUE);

        startButton = new JButton("START");
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(buttonFont);

        startButton.addActionListener(e -> {
            try {
                new Game().setVisible(true);
                //frame.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        //frame.setSize(1280, 719);
        //frame.setSize(1280, 720);

        frame.setVisible(true);
    }
}