package com.company;

import javax.swing.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
       Multiplayer MP = new Multiplayer();
       MP.StartServer("localhost", 22222);




       /* boolean online = false;
        boolean isDriver = true;
        String name = "";
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if(command.equals("online")){
            online = true;
            command = scanner.nextLine();
            if(command.equals("drive")){
                isDriver = true;
                command = scanner.nextLine();
                name = command;
            }
            if(command.equals("spam")) {
                isDriver = false;
            }
        }
        if(command.equals("desktop")){
            online = false;
            command = scanner.nextLine();
            name = command;
        }
        if(online) {
            if(isDriver) {
                Multiplayer MP = new Multiplayer();
                MP.InitServer("localhost", 22222, name);
            }else{
                Multiplayer MP = new Multiplayer();
                MP.connect("localhost", 22222, name);
            }
        }else{
            RoadLogic roadLogic = new RoadLogic(false);
            RaceLayout RL = new RaceLayout();
            RL.StartLayout(1, roadLogic, name);
        }
        */
    }
}
