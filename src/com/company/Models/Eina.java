package com.company.Models;

import com.company.Game;
import com.company.Logic;

import java.awt.*;
import java.io.IOException;

public abstract class Eina {

    private int x;
    private int y;
    private int costat = 100;
    private Game game;
    Logic logic = new Logic();

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

    public void move(Game game, int moviment) throws IOException {
        if (y < 525) {
            y = y + moviment;
            logic.collision(game);
        } else {
            y = y + 50;
            game.eines.remove(this);
        }
    };

    public Rectangle getBounds() {
        return new Rectangle(x, y, costat, costat);
    }

    public abstract void paint(Graphics2D g);
}