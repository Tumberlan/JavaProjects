package com.company;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Coder {
    File f_in = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "test.txt");

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
                while (value != null) {
                    value = value.toUpperCase(Locale.ROOT);
                    String parse_symbol = " ";
                    String[] sub_str = value.split(parse_symbol);
                    for (String s : sub_str) {
                        String[] sub_sub_str = s.split("");
                        for (int i = 0; i < sub_sub_str.length; i++) {
                            String answer = c.CodeDictionary.get(sub_sub_str[i]);
                            if(i == sub_sub_str.length - 1){
                                printStream.print(answer);
                                System.out.print(answer);
                            }else{
                                printStream.print(answer + " ");
                                System.out.print(answer + " ");
                            }
                        }
                        printStream.print("   ");
                        System.out.print("   ");
                    }
                    value = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void decoding(){
        try {
            FileOutputStream fos = new FileOutputStream("C://Users//Igor//ProgProjects//JavaProjects//Lab1//output.txt");
            PrintStream printStream = new PrintStream(fos);

            try {
                FileReader fr = new FileReader(f_in);
                BufferedReader reader = new BufferedReader(fr);
                String value = reader.readLine();
                while (value != null) {
                    String parse_symbol = "   ";
                    String[] sub_str = value.split(parse_symbol);
                    for (String s : sub_str) {
                        String[] sub_sub_str = s.split(" ");
                        for (int i = 0; i < sub_sub_str.length; i++) {
                            String answer = c.DecodeDictionary.get(sub_sub_str[i]);
                            printStream.print(answer);
                            System.out.print(answer);
                        }
                        printStream.print(" ");
                        System.out.print(" ");
                    }
                    value = reader.readLine();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void take_command(String command){
        if(command.equals("code")){
            coding();
        }else if(command.equals("decode")){
            decoding();
        }
    }
}
