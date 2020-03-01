package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    public BufferedImage carregaImatge(String nomFitxer) throws IOException {

        return ImageIO.read(this.getClass().getResource("Imatges/" + nomFitxer));
    }
}
