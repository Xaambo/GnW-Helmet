package com.company;

import com.company.Models.*;

import java.io.IOException;

public class Logic {

    Thread efectes;

    public boolean collision(Game game) throws IOException {

        int vides = game.player.vides;
        Eina eina;

        for (int i = 0; i < game.eines.size(); i++) {
            if (game.player.getBounds().intersects(game.eines.get(i).getBounds())) {

                if (!game.player.escut) {

                    eina = game.eines.get(i);
                    game.eines.remove(i);

                    if (eina instanceof RainbowCat) {

                        vides = vides - 2;
                        game.player.x = 30;

                    } else if (eina instanceof Martell) {

                        vides = vides - 1;
                        efectesPlayer(2000, 0, false, game);

                    } else if (eina instanceof Tornavis) {

                        vides = vides - 1;
                        efectesPlayer(3000, -1, false, game);

                    } else if (eina instanceof Vida) {

                        vides = 10;

                    } else if (eina instanceof Escut) {

                        efectesPlayer(5000, 1, true, game);

                    }

                    game.player.vides = vides;

                    if (vides <= 0) {
                        /*efectes = new Thread(game::gameOver);
                        efectes.start();*/

                        game.gameOver();

                        /** Modificar aquesta part per que es pari tot el joc amb el GameOver */
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public static int randomNum(int finsA) {

        //EL 0 ESTA INCLUIT

        int random = (int) Math.floor(Math.random() * finsA);

        return random;
    }

    public void efectesPlayer(int tempsParat, int efecte, Boolean escut, Game game) {

        game.player.intercanvi = efecte;
        game.player.escut = escut;

        efectes = new Thread(() -> {

            try {
                Thread.sleep(tempsParat);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            game.player.intercanvi = 1;
            game.player.escut = false;
        });

        efectes.start();

    }
}
