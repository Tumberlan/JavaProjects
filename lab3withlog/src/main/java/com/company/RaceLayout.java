package com.company;

import javax.swing.*;

public class RaceLayout {
    JFrame f = new JFrame("Java F1");
    public static int WINDOW_WIDTH = 1200;
    public static int WINDOW_HEIGHT = 655;
    RoadLogic RL = new RoadLogic(false);
    public void StartLayout(){
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        f.add(new Road(1, RL));
        f.setVisible(true);
    }
}
