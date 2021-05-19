package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioThread implements Runnable{

    @Override
    public void run(){
        try {
            FileInputStream file = new FileInputStream("src/res/Juice WRLD - Fast.mp3");
            Player playMp3 = new Player(file);
            playMp3.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }

    }
}
