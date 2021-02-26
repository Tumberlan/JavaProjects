package com.company;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Coder {
    File f_in = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "test.txt");
    File f_out = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "output.txt");
    File file;

    CodeMorse c;

    public void take_file(File f){
        file = f;
        c = new CodeMorse(file);
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
                CodeMorse tmp = new CodeMorse(file);
                tmp.FillDictionaries();
                boolean tick = false;
                while (value != null) {
                    if(tick){
                        ads.append("\n");
                    }else{
                        tick = true;
                    }
                    value = value.toUpperCase(Locale.ROOT);
                    String parse_symbol = " ";
                    String[] sub_str = value.split(parse_symbol);
                    for (String s : sub_str) {
                        String[] sub_sub_str = s.split("");
                        for (int i = 0; i < sub_sub_str.length; i++) {
                            String answer = c.CodeDictionary.get(sub_sub_str[i]);
                            /*if(answer != null) {
                                int amount = Integer.parseInt(tmp.AmountDictionary.get(sub_sub_str[i]));
                                amount++;
                                tmp.AmountDictionary.remove(sub_sub_str[i]);
                                tmp.AmountDictionary.put(sub_sub_str[i], Integer.toString(amount));
                            }*/
                            if(answer != null) {
                                if(!sub_sub_str[i].equals("")) {
                                    tmp.calc.add(sub_sub_str[i].charAt(0));
                                }
                            }
                            if (i == sub_sub_str.length - 1) {
                                ads.append(answer);
                            } else {
                                ads.append(answer).append(" ");
                            }
                        }
                        ads.append("   ");

                    }
                    value = reader.readLine();
                }
                //ads.append("_________");
                printStream.println(ads.toString());
                System.out.println(ads.toString());
                c.calc = tmp.calc;
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
                CodeMorse tmp = new CodeMorse(file);
                tmp.FillDictionaries();
                boolean tick = false;
                while (value != null) {
                    if(tick){
                        ads.append("\n");
                    }else{
                        tick = true;
                    }
                    String parse_symbol = "   ";
                    String[] sub_str = value.split(parse_symbol);
                    for (String s : sub_str) {
                        String[] sub_sub_str = s.split(" ");
                        for (String item : sub_sub_str) {

                            String answer = c.DecodeDictionary.get(item);
                            /*if(answer != null) {
                                int amount = Integer.parseInt(tmp.AmountDictionary.get(answer));
                                amount++;
                                tmp.AmountDictionary.remove(answer);
                                tmp.AmountDictionary.put(answer, Integer.toString(amount));
                            }*/
                            if(answer != null) {
                                if(!answer.equals("")) {
                                    tmp.calc.add(answer.charAt(0));
                                }
                            }

                            ads.append(answer);
                        }
                        ads.append(" ");
                    }

                    value = reader.readLine();
                }
                printStream.println(ads.toString());
                System.out.println(ads.toString());
                c.calc = tmp.calc;
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
