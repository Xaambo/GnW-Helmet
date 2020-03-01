package com.company;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {

    Clip nextRound;
    Clip background;

    public void playSound(String name) {
        try {

            // The sound dependency that i used only worked with .wav files

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("Audios/" + name + ".wav"));
            nextRound = AudioSystem.getClip();
            nextRound.open(audioIn);
            nextRound.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playLoop(String name) {
        try {

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("Audios/" + name + ".wav"));
            background = AudioSystem.getClip();
            background.open(audioIn);
            background.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        background.close();
    }
}