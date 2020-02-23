package com.company;

public class Logic {

    protected boolean collision(Game game) {

        for (int i = 0; i < game.eines.size(); i++) {
            if (game.player.getBounds().intersects(game.eines.get(i).getBounds())) {

                game.player.vides = game.player.vides - 1;
                game.eines.remove(i);

                return true;
            }
        }

        return false;
    }
}
