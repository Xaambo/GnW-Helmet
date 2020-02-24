package com.company;

import com.company.Models.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable {

    private final AtomicBoolean pressed = new AtomicBoolean(false);
    Player player = new Player(this);
    public ArrayList<Eina> eines = new ArrayList<>();
    int punts = 0;
    int ronda = 0;
    int temps = 500;
    Thread t1, t2;

    private final static int PRIMER = 95;
    private final static int SEGON = 170;
    private final static int TERCER = 245;
    private final static int QUART = 320;

    private ArrayList<Integer> posicions = new ArrayList<>() {{
        add(PRIMER);
        add(SEGON);
        add(TERCER);
        add(QUART);
    }};

    Logic logic = new Logic();

    public static void main(String[] args) throws InterruptedException {
        Game programa = new Game();
        programa.iniciar();
    }

    public void iniciar() throws InterruptedException {

        t1 = new Thread(this);
        JFrame frame = new JFrame("Game & Watch: Helmet JOJO EDITION");
        frame.add(this);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t2 = new Thread(() -> {
            while (true) {
                if (eines.size() < 20) {
                    eines.add(crearEina(Game.this));
                    try {
                        Thread.sleep(temps * 5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

        while (true) {
            movimentEines(this);
            Thread.sleep(temps);
        }
    }

    private Eina crearEina(Game game) {

        int eina;
        int posicio;

        Eina einaSeleccionada = null;

        eina = logic.randomNum(5);
        posicio = logic.randomNum(4);

        switch (eina) {
            case 0:
                einaSeleccionada = new Martell(posicions.get(posicio), 0, game);
                break;
            case 1:
                einaSeleccionada = new Tornavis(posicions.get(posicio), 0, game);
                break;
            case 2:
                einaSeleccionada = new Clau(posicions.get(posicio), 0, game);
                break;
            case 3:
                einaSeleccionada = new Escut(posicions.get(posicio), 0, game);
                break;
            case 4:
                einaSeleccionada = new Vida(posicions.get(posicio), 0, game);
                break;
        }
        return einaSeleccionada;
    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

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
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));
        g2d.drawString("Vides: " + player.vides, 10, 30);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));
        g2d.drawString("Puntuació: " + punts, 325, 30);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));
        g2d.drawString("Ronda: " + ronda, 365, 60);
    }

    public ArrayList<Eina> movimentEines(Game game) {

        for (int i = 0; i < eines.size(); i++) {
            eines.get(i).move(game);
        }
        return eines;
    }

    public void gameOver(Game game) {
        //Sound.BACK.stop();
        JOptionPane.showMessageDialog(this, "Has mort amb una puntuació de: " + game.player.vides,
                "U DEEEED LMAOOOO", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    @Override
    public void run() {

        while (true) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}