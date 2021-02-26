package com.company;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public final class Command {

    File EnFile = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "En_file.txt");
    File RuFile = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "Ru_file.txt");
    File OwnFile = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "Own_file.txt");
    Coder coder = new Coder();


    void take_command(boolean setter){
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if(setter) {
            if (command.equals("1")) {
                coder.take_file(EnFile);
                take_command(false);
            } else if (command.equals("2")) {
                coder.take_file(RuFile);
                take_command(false);
            } else if (command.equals("3")) {
                Desktop desktop = null;
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    assert desktop != null;
                    desktop.open(OwnFile);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                coder.take_file(OwnFile);
                take_command(false);
            } else {
                System.out.println("You can only use '1' for English file, '2' for Russian and '3' for your own");
                take_command(true);
            }
        }else{
            if(command.equals("code")){
                coder.coding();
                take_command(false);
            }else if(command.equals("decode")){
                coder.decoding();
                take_command(false);
            }else if(command.equals("count")){
                coder.c.Calc.WriteAmount();
                take_command(false);
            }else if(command.equals("edit file")){
                System.out.println("Choose file 'test' or 'output'");
                Desktop desktop = null;
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                boolean checker = true;
                while(checker) {
                    in = new Scanner(System.in);
                    command = in.nextLine();
                    if (command.equals("test")) {
                        try {
                            assert desktop != null;
                            desktop.open(coder.fIn);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                        checker = false;
                    } else if (command.equals("output")) {
                        try {
                            assert desktop != null;
                            desktop.open(coder.fOut);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                        checker = false;
                    }else{
                        System.out.println("Wrong file name! Please choose file 'test' or 'output'");
                    }
                }
                take_command(false);
            } else if(!command.equals("end")) {
                System.out.println("Wrong command! Please use 'code', 'decode', 'edit file' or 'end'");
                take_command(false);
            }
        }
    }
}
