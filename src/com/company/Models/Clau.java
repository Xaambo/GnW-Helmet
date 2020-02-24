package com.company.Models;

import com.company.Game;

import java.awt.*;

public class Clau extends Eina {

    public Clau(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setPaint(Color.yellow);
        g.fillRect(getX(), getY(), getCostat(), getCostat());
    }

}
