package com.company;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {

        Multiplayer MP = new Multiplayer();
        MP.InitServer("localhost", 22222);
        /*FirstLayout FL = new FirstLayout();
        FL.StartLayout();*/

    }
}
