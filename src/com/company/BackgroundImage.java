package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundImage extends JPanel {

    private BufferedImage image;
    private int frameSizeWidth;
    private int frameSizeHeight;

    public BackgroundImage(BufferedImage image, int frameSizeWidth, int frameSizeHeight) {
        this.image = image;
        this.frameSizeWidth = frameSizeWidth;
        this.frameSizeHeight = frameSizeHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, frameSizeWidth, frameSizeHeight, this);
    }
}
