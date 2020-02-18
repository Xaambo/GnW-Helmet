package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    private static final int Y = 305;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 40;

    int x = 20;
    int xa = 0;
    private Game game;

    public Player(Game game) {
        this.game= game;
    }

    public void move() {
        if (x + xa > 10 && x + xa < game.getWidth() - 20) {
            x = x + xa;
        }
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -75;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = 75;
        }

        move();
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }
}
