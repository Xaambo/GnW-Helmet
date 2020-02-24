package com.company.Models;

import com.company.Game;

import java.awt.*;

public class Vida extends Eina {

    public Vida(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setPaint(Color.green);
        g.fillRect(getX(), getY(), getCostat(), getCostat());
    }

}
