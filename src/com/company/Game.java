package com.company;

import com.company.Models.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel {

    private final AtomicBoolean pressed = new AtomicBoolean(false);
    AtomicBoolean paused = new AtomicBoolean(false);
    AtomicBoolean gameOver = new AtomicBoolean(false);
    Player player = new Player(this);
    Porta porta = new Porta(this);
    public ArrayList<Eina> eines = new ArrayList<>();
    Sound sound = new Sound();
    int punts = 0;
    int ronda = 0;
    int temps = 10;
    int creacioEines = 500;
    int movimentEina = 5;
    Thread t1, t2, t3;
    BufferedImage backgroundImage = ImageIO.read(this.getClass().getResource("Imatges/Background.jpg"));

    private final static int PRIMER = 190;
    private final static int SEGON = 350;
    private final static int TERCER = 510;
    private final static int QUART = 670;
    private final static int CINQUE = 830;
    private final static int SISE = 990;

    private ArrayList<Integer> posicions = new ArrayList<>() {{
        add(PRIMER);
        add(SEGON);
        add(TERCER);
        add(QUART);
        add(CINQUE);
        add(SISE);
    }};

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Game programa = new Game();
                programa.iniciar();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void iniciar() {

        System.setProperty("sun.java2d.opengl","True");

        JFrame frame = new JFrame("Game N' Watch: Helmet GILGAMESH EDITION");

        frame.add(this);
        frame.setSize(1280, 720);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t1 = new Thread(() -> {
            while (!gameOver.get()) {
                try {
                    movimentEines(Game.this);
                    Thread.sleep(temps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2 = new Thread(() -> {
            while (!gameOver.get()) {
                if (eines.size() < 20 && !paused.get()) {
                    try {
                        eines.add(crearEina(Game.this));
                        Thread.sleep(creacioEines);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t3 = new Thread(() -> {
            while (!gameOver.get()) {
                if (!paused.get()) {
                    try {
                        porta.OpenAndClose();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        while (true) {
            this.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Eina crearEina(Game game) throws IOException {

        int eina;
        int posicio;

        Eina einaSeleccionada = null;

        eina = Logic.randomNum(100);
        posicio = Logic.randomNum(6);

        if (eina < 10)
            einaSeleccionada = new Escut(posicions.get(posicio), 0, game);
        else if (eina < 20)
            einaSeleccionada = new Vida(posicions.get(posicio), 0, game);
        else if (eina < 47)
            einaSeleccionada = new Martell(posicions.get(posicio), 0, game);
        else if (eina < 74)
            einaSeleccionada = new Tornavis(posicions.get(posicio), 0, game);
        else if (eina < 100)
            einaSeleccionada = new RainbowCat(posicions.get(posicio), 0, game);

        return einaSeleccionada;
    }

    public Game() throws IOException {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (pressed.compareAndSet(false, true) && !paused.get()) {
                    player.keyPressed(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressed.set(false);
            }
        });
        setFocusable(true);

        sound.playLoop("fate");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        porta.paint(g2d);
        player.paint(g2d);

        for (int i = 0; i < eines.size(); i++) {
            eines.get(i).paint(g2d);
        }

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));
        g2d.drawString("Vides: " + player.vides, 10, 30);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));
        g2d.drawString("Puntuació: " + punts, 1100, 30);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));
        g2d.drawString("Ronda: " + ronda, 1142, 60);
    }

    public ArrayList<Eina> movimentEines(Game game) {

        for (int i = 0; i < eines.size(); i++) {
            eines.get(i).move(game, movimentEina);
        }
        return eines;
    }

    public void gameOver() {

        gameOver.set(true);

        sound.close();
        sound.playSound("gilgamesh2");
        JOptionPane.showMessageDialog(this, "ZASSHU DONO \n" +
                        "Rondes: " + ronda + "\n" +
                        "Puntuació ronda final: " + punts,
                "DOKO DA... BAASAKA", JOptionPane.ERROR_MESSAGE);
        System.exit(ABORT);
    }

    /*@Override
    public void run() {

        while (true) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}