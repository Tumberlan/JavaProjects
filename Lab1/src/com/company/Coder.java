package com.company;

import java.awt.*;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Coder {
    File f_in = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "test.txt");
    File f_out = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "output.txt");

    CodeMorse c = new CodeMorse();
    Coder(){
        c.FillDictionaries();
    }

    public void read(char[] array, int amountOfSymbols, Scanner in){

        Reader reader = null;
        try{
            reader =  new InputStreamReader(new FileInputStream("test.txt"));
        }catch (IOException e){
            System.err.println("Error during reading file: " + e.getLocalizedMessage());
        }
        finally {
            if(reader != null){
                try{
                    int a = reader.read(array, 0, amountOfSymbols);
                    in.close();
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public void coding(){

        try {
            FileOutputStream fos = new FileOutputStream("C://Users//Igor//ProgProjects//JavaProjects//Lab1//output.txt");
            PrintStream printStream = new PrintStream(fos);

            try {
                FileReader fr = new FileReader(f_in);
                BufferedReader reader = new BufferedReader(fr);
                String value = reader.readLine();
                StringBuilder ads = new StringBuilder();
                while (value != null) {
                    value = value.toUpperCase(Locale.ROOT);
                    String parse_symbol = " ";
                    String[] sub_str = value.split(parse_symbol);
                    for (String s : sub_str) {
                        String[] sub_sub_str = s.split("");
                        for (int i = 0; i < sub_sub_str.length; i++) {
                            String answer = c.CodeDictionary.get(sub_sub_str[i]);
                            if(i == sub_sub_str.length - 1){
                                ads.append(answer);
                            }else{
                                ads.append(answer).append(" ");
                            }
                        }
                        ads.append("   ");
                    }
                    value = reader.readLine();
                }
                printStream.println(ads.toString());
                System.out.println(ads.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void decoding(){
        try {
            FileOutputStream fos = new FileOutputStream("C://Users//Igor//ProgProjects//JavaProjects//Lab1//test.txt");
            PrintStream printStream = new PrintStream(fos);

            try {
                FileReader fr = new FileReader(f_out);
                BufferedReader reader = new BufferedReader(fr);
                String value = reader.readLine();
                StringBuilder ads = new StringBuilder();
                while (value != null) {
                    String parse_symbol = "   ";
                    String[] sub_str = value.split(parse_symbol);
                    for (String s : sub_str) {
                        String[] sub_sub_str = s.split(" ");
                        for (String item : sub_sub_str) {
                            String answer = c.DecodeDictionary.get(item);
                            ads.append(answer);
                        }
                        ads.append(" ");
                    }
                    value = reader.readLine();
                }
                printStream.println(ads.toString());
                System.out.println(ads.toString());
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void take_command(){
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        if(command.equals("code")){
            coding();
            take_command();
        }else if(command.equals("decode")){
            decoding();
            take_command();
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
                        desktop.open(f_in);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    checker = false;
                } else if (command.equals("output")) {
                    try {
                        assert desktop != null;
                        desktop.open(f_out);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    checker = false;
                }else{
                    System.out.println("Wrong file name! Please choose file 'test' or 'output'");
                }
            }
            take_command();
        } else if(!command.equals("end")) {
            System.out.println("Wrong command! Please use 'code', 'decode', 'edit file' or 'end'");
            take_command();
        }

    }
}
