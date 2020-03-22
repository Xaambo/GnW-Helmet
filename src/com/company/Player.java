package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    private Image imatges = new Image();
    private Sound sound = new Sound();

    BufferedImage image;
    ImageIcon imageGIF;
    ImageIcon imageGIFreves;

    private static final int Y = 575;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 94;

    int x = 30;
    int vides = 10;
    int intercanvi = 1;
    int direccio = 0;
    boolean escut = false;
    private Game game;
    private Thread t;

    public Player(Game game) throws IOException {
        this.game = game;
        image = imatges.carregaImatge("ArcherGilgamesh.png");
        imageGIF = imatges.carregaGIF("gilgamesh2.gif");
        imageGIFreves = imatges.carregaGIF("gilgamesh2reves.gif");
    }

    public void move(int moviment) {

        moviment = moviment * intercanvi;

        if (x + moviment > 10 && x + moviment < game.getWidth() - 20) {

            if (((x + moviment) >= 1120) && game.porta.isOpen()) {

                x = x + moviment;

                intercanvi = 0;

                t = new Thread(() -> {
                    try {
                        nextRound();
                        intercanvi = 1;
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                });

                t.start();

                game.punts = game.punts + 5;

                if (game.punts == 30) {

                    game.punts = 0;
                    game.ronda = game.ronda + 1;

                    if (game.movimentEina < 50 && game.creacioEines > 150) {

                        game.movimentEina = game.movimentEina + 1;
                        game.creacioEines = game.creacioEines - 30;

                    }
                }

            } else if ((x + moviment) < (game.getWidth() - 160)) {
                x = x + moviment;
            }
        }
    }

    public void nextRound() throws InterruptedException, IOException {

        int zashu = (Logic.randomNum(3) + 1);
        int threadSleep = 0;

        image = imatges.carregaImatge("ArcherGilgamesh2.png");

        sound.playSound("zashu" + zashu);
        game.paused.set(true);

        switch (zashu) {
            case 1:
                threadSleep = 1470;
                break;
            case 2:
                threadSleep = 600;
                break;
            case 3:
                threadSleep = 2123;
                break;
        }

        Thread.sleep(threadSleep);

        game.paused.set(false);
        image = imatges.carregaImatge("ArcherGilgamesh.png");
        game.player.x = 30;

    }

    public void keyPressed(KeyEvent e) {

        int moviment = 0;

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moviment = -160;
            direccio = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moviment = 160;
            direccio = 0;
        }

        move(moviment);
    }

    public void paint(Graphics2D g) {

        if (!game.paused.get()) {
            //g.drawImage(imageGIF, x, Y, WIDTH, HEIGHT, null);
            if (direccio == 0)
                imageGIF.paintIcon(game, g, x, Y);
            else
                imageGIFreves.paintIcon(game, g, x, Y);
        } else {
            g.drawImage(image, x, Y - 70, WIDTH, 164, null);
        }
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }
}
