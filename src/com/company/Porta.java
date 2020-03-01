package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Porta {

    private Image imatges = new Image();
    private Sound sound = new Sound();

    BufferedImage image;

    private static final int X = 1140;
    private static final int Y = 490;
    private static final int WIDTH = 120;
    private static final int HEIGHT = 180;

    private Game game;
    private boolean isOpen = false;

    public Porta(Game game) throws IOException {
        this.game = game;
        image = imatges.carregaImatge("Porta.png");
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void OpenAndClose() {
        isOpen = !isOpen;
    }

    public void paint(Graphics2D g) {

        g.setColor(Color.decode("#d4af37"));
        g.fillRect(X + 20, Y, WIDTH - 42, HEIGHT);

        if (isOpen)
            g.drawImage(image, X + 42, Y, -WIDTH, HEIGHT, null);
        else
            g.drawImage(image, X, Y, WIDTH, HEIGHT, null);

        g.setStroke(new BasicStroke(3));

        g.setColor(Color.black);

        g.drawLine(X + 20, Y, X + WIDTH - 22, Y);
        g.drawLine(X + 20, Y, X + 20, Y + HEIGHT - 2);
        g.drawLine(X + WIDTH - 22, Y, X + WIDTH - 22, Y + HEIGHT - 2);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
