package com.company.Models;

import com.company.Game;
import com.company.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RainbowCat extends Eina {

    private com.company.Image imatges = new Image();

    BufferedImage image;

    public RainbowCat(int x, int y, Game game) throws IOException {
        super(x, y, game);
        image = imatges.carregaImatge("Vida.png");
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(image, getX(), getY(), getCostat(), getCostat(), null);
    }

}
