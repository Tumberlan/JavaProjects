package com.company;

import javax.swing.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        boolean online = false;
        boolean isDriver = true;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if(command.equals("online")){
            online = true;
            command = scanner.nextLine();
            if(command.equals("drive")){
                isDriver = true;
            }
            if(command.equals("spam")) {
                isDriver = false;
            }
        }
        if(command.equals("desktop")){
            online = false;
        }
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
