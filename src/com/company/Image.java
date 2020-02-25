package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    public BufferedImage carregaImatge(String nomFitxer) throws IOException {

        BufferedImage image = ImageIO.read(new File("Imatges/" + nomFitxer));

        return image;
    }
}
