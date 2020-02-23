package com.company;

public class Logic {

    protected boolean collision(Game game) {

        int vides = game.player.vides;

        for (int i = 0; i < game.eines.size(); i++) {
            if (game.player.getBounds().intersects(game.eines.get(i).getBounds())) {

                vides = vides - 1;
                game.eines.remove(i);

                game.player.vides = vides;

                if (vides == 0) {
                    game.gameOver(game);
                }

                return true;
            }
        }

        return false;
    }
}
