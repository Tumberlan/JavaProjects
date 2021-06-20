package com.company;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        boolean online = false;
        boolean isDriver = true;
        online = true;
        if(online) {
            if(isDriver) {
                Multiplayer MP = new Multiplayer();
                MP.InitServer("localhost", 22222);
            }else{
                Multiplayer MP = new Multiplayer();
                MP.connect("localhost", 22222);
            }
        }else{
            RoadLogic roadLogic = new RoadLogic(false);
            RaceLayout RL = new RaceLayout();
            RL.StartLayout(1, roadLogic);
        }
        /*FirstLayout FL = new FirstLayout();
        FL.StartLayout();*/

    }
}
