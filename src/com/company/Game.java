package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

    int speed = 2;
    private final AtomicBoolean pressed = new AtomicBoolean(false);
    Player player = new Player(this);
    ArrayList<Eina> eines = new ArrayList<>();
    int moviment = 0;
    //private Game game;

    public static void main(String[] args) throws InterruptedException {
        Game programa = new Game();
        programa.iniciar();
    }

    public void iniciar() throws InterruptedException {
        JFrame frame = new JFrame("Game & Watch: Helmet JOJO EDITION");
        Game game = new Game();
        frame.add(game);
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread t = new Thread(() -> {
            try {
                movimentEines(game);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }

    private Eina crearEina(Game game) {

        int numeroRandom;
        Eina eina;

        numeroRandom = (int) Math.floor(Math.random() * 4);

        switch (numeroRandom) {
            case 0:
                eina = new Martell(95, 0, game);
                break;
            case 1:
                eina = new Martell(170, 0, game);
                break;
            case 2:
                eina = new Martell(245, 0, game);
                break;
            case 3:
                eina = new Martell(320, 0, game);
                break;
            default:
                eina = new Martell(0, 0, game);
        }

        return eina;
    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (pressed.compareAndSet(false, true)) {
                    moviment = player.keyPressed(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressed.set(false);
            }
        });
        setFocusable(true);
        //Sound.BACK.loop();
    }

    private void move() {

        moviment = player.move(moviment);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        player.paint(g2d);

        for (int i = 0; i < eines.size(); i++) {
            eines.get(i).paint(g2d);
        }

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(player.vides), 10, 30);
    }

    public void movimentEines(Game game) throws InterruptedException {
        while (true) {
            if (eines.size() < 8) {
                eines.add(crearEina(game));
            }

            for (int i = 0; i < eines.size(); i++) {
                eines.get(i).move(game);
            }

            game.repaint();

            Thread.sleep(500);
        }
    }

    public void gameOver(Game game) {
        //Sound.BACK.stop();
        JOptionPane.showMessageDialog(this, "Has mort amb una puntuació de: " + game.player.vides,
                "U DEEEED LMAOOOO", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
}