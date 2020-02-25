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

    private static final int Y = 205;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 50;

    int x = 20;
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

            } else if((x + moviment) >= 390) {

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

            } else {
                x = x + moviment;
            }

            moviment = 0;
        }

        return moviment;
    }

    public void keyPressed(KeyEvent e) {

        int moviment = 0;

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moviment = -75;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moviment = 75;
        }

        move(moviment);
    }

    public void paint(Graphics2D g) throws IOException {

        g.drawImage(image, x, Y, WIDTH, HEIGHT, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }
}
