package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

    int speed = 2;
    private final AtomicBoolean pressed = new AtomicBoolean(false);
    Player player = new Player(this);

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Game & Watch: Helmet JOJO EDITION");
        Game game = new Game();
        frame.add(game);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }

    private int getScore() {
        return speed - 2;
    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (pressed.compareAndSet(false, true)) {
                    player.keyPressed(e);
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

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        player.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(getScore()), 10, 30);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Has mort amb una puntuació de: " + getScore(),
                "U DEEEED LMAOOOO", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
}