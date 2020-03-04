package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    public BufferedImage carregaImatge(String nomFitxer) throws IOException {

        return ImageIO.read(this.getClass().getResource("Imatges/" + nomFitxer));
    }

    public ImageIcon carregaGIF(String nomFitxer) {

        return new ImageIcon(this.getClass().getResource("Imatges/" + nomFitxer));
    }
}
