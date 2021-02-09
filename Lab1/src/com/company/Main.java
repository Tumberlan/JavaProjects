package com.company;

import java.lang.String;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Coder coder = new Coder();
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        coder.take_command(command);

    }



}
