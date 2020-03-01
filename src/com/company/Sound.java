package com.company;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {

    Clip nexRound;
    Clip bacground;

    public void playSound(String name) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("Audios/" + name + ".wav"));
        nexRound = AudioSystem.getClip();
        nexRound.open(audioIn);
        nexRound.start();
    }

    public void playLoop(String name) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("Audios/" + name + ".wav"));
        bacground = AudioSystem.getClip();
        bacground.open(audioIn);
        bacground.loop(Clip.LOOP_CONTINUOUSLY);
    }
}