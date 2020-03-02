package com.company.Models;

import com.company.Game;
import com.company.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Vida extends Eina {

    private Image imatges = new Image();

    BufferedImage image;

    public Vida(int x, int y, Game game) throws IOException {
        super(x, y, game);
        image = imatges.carregaImatge("Vida.png");
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(image, getX(), getY(), getCostat(), getCostat(), null);
    }
}