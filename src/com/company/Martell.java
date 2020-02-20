package com.company;

import java.awt.*;

public class Martell extends Eina {

    public Martell(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void paint(Graphics2D g) {
        g.fillRect(getX(), getY(), getCostat(), getCostat());
    }
}
