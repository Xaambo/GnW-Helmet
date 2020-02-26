package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    private Image imatges = new Image();

    BufferedImage image;

    private static final int Y = 520;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 141;

    int x = 30;
    int vides = 10;
    int intercanvi = 1;
    boolean escut = false;
    private Game game;

    public Player(Game game) throws IOException {
        this.game = game;
        image = imatges.carregaImatge("ArcherGilgamesh.png");
    }

    public int move(int moviment) {

        moviment = moviment * intercanvi;

        if (x + moviment > 10 && x + moviment < game.getWidth() - 20) {
            //logic.collision(this.game);
            if (vides <= 0) {

                game.gameOver(game);

            } else if(((x + moviment) >= 1120) /*&& game.porta.isClosed()*/) {

                x = x + moviment;
                x = 20;

                game.punts = game.punts + 5;

                if (game.punts == 30) {
                    game.punts = 0;
                    game.ronda = game.ronda + 1;
                    if (game.temps > 100) {
                        game.temps = game.temps - 100;
                    }
                }

            } else if ((x + moviment) < game.getWidth()) {
                x = x + moviment;
            }

            moviment = 0;
        }

        return moviment;
    }

    public void keyPressed(KeyEvent e) {

        int moviment = 0;

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moviment = -160;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moviment = 160;
        }

        move(moviment);
    }

    public void paint(Graphics2D g) {

        g.drawImage(image, x, Y, WIDTH, HEIGHT, null);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }
}
