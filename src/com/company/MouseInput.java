package com.company;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private Game game;
    protected Sound sound;

    public MouseInput(Game game, Sound sound) {
        this.game = game;
        //this.sound = sound;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (!game.isGame) {
            if (x >= 520 && x <= 760) {
                if (y >= 210 && y <= 300) {
                    game.isGame = true;
                    game.paused.set(false);
                    //sound.playLoop("fate");
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
