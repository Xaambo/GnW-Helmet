package com.company;

import java.awt.*;

public class Martell extends Eina {

    public Martell(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setPaint(Color.red);
        g.fillRect(getX(), getY(), getCostat(), getCostat());
    }

}
