package com.company;

import org.apache.log4j.BasicConfigurator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        RunLogGame RLG = new RunLogGame();
        RLG.RunGame();
    }
}
