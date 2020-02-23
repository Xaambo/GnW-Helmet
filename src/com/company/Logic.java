package com.company;

public class Logic {

    protected boolean collision(Game game) {

        int vides = game.player.vides;
        Eina eina;

        for (int i = 0; i < game.eines.size(); i++) {
            if (game.player.getBounds().intersects(game.eines.get(i).getBounds())) {

                eina = game.eines.get(i);
                game.eines.remove(i);

                if (eina instanceof Martell) {

                    vides = vides - 2;
                    game.player.x = 20;

                } else if (eina instanceof Tornavis) {

                    vides = vides - 2;

                }

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
