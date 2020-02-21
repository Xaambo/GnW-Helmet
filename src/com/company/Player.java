package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    private static final int Y = 305;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 40;

    int x = 20;
    int vides = 10;
    private Game game;
    private Logic logic = new Logic();

    public Player(Game game) {
        this.game = game;
    }

    public int move(int moviment) {
        if (x + moviment > 10 && x + moviment < game.getWidth() - 20) {
            if (logic.collision(game)) {
                vides = vides - 1;

            } else if (vides <= 0) {
                game.gameOver(game);
            } else {
                x = x + moviment;
            }

            moviment = 0;
        }

        return moviment;
    }

    public int keyPressed(KeyEvent e) {

        int moviment = 0;

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moviment = -75;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moviment = 75;
        }

        return moviment;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }
}
