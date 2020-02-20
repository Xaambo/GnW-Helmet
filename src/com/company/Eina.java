package com.company;

import java.awt.*;

public abstract class Eina {

    private int x;
    private int y;
    private int costat = 30;
    private Game game;

    public Eina(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCostat() {
        return costat;
    }

    public void move(Game game) {
        if (y < 310) {
            y = y + 10;
        } else {
            game.eines.remove(this);
        }
    };

    public Rectangle getBounds() {
        return new Rectangle(x, y, costat, costat);
    }

    public void paint(Graphics2D g) { g.fillRect(x, y, costat, costat); };
}