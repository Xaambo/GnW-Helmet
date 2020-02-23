package com.company;

import java.awt.*;

public class Tornavis extends Eina {

    public Tornavis(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setPaint(Color.green);
        g.fillRect(getX(), getY(), getCostat(), getCostat());
    }

}
