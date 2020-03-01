package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

public class Player {

    private Image imatges = new Image();

    BufferedImage image;

    private static final int Y = 520;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 164;

    int x = 30;
    int vides = 10;
    int intercanvi = 1;
    boolean escut = false;
    private Game game;
    Thread t;

    public Player(Game game) throws IOException {
        this.game = game;
        image = imatges.carregaImatge("ArcherGilgamesh.png");
    }

    public int move(int moviment) throws InterruptedException {

        moviment = moviment * intercanvi;

        if (x + moviment > 10 && x + moviment < game.getWidth() - 20) {
            //logic.collision(this.game);
            if (vides <= 0) {

                game.gameOver(game);

            } else if(((x + moviment) >= 1120) /*&& game.porta.isClosed()*/) {

                x = x + moviment;

                intercanvi = 0;

                t = new Thread(() -> {
                    try {
                        nextRound();
                        intercanvi = 1;
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                t.start();

                //Thread.sleep(5000);

                //x = 20;

                game.punts = game.punts + 5;

                if (game.punts == 5) {
                    game.punts = 0;
                    game.ronda = game.ronda + 1;
                    if (game.movimentEina < 50) {
                        game.movimentEina = game.movimentEina + 1;
                    }
                }

            } else if ((x + moviment) < (game.getWidth() - 160)) {
                x = x + moviment;
            }

            moviment = 0;
        }

        return moviment;
    }

    public void nextRound() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {

        AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("Audios/zashu1.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
        game.paused.set(true);
        Thread.sleep(1470);
        game.paused.set(false);
        game.player.x = 20;

    }

    public void keyPressed(KeyEvent e) throws InterruptedException {

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
