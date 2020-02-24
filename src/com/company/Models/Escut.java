package com.company.Models;

import com.company.Game;

import java.awt.*;

public class Escut extends Eina {

    public Escut(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setPaint(Color.blue);
        g.fillRect(getX(), getY(), getCostat(), getCostat());
    }

}
