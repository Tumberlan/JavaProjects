package com.company;

import java.io.*;
import java.util.Locale;


public class Coder {
    File fIn = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "test.txt");
    File fOut = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "output.txt");
    File file;

    CodeMorse c;

    public void take_file(File f){
        file = f;
        c = new CodeMorse(file);
        c.FillDictionaries();
    }


    public void coding(){

        try {
            FileOutputStream fos = new FileOutputStream("C://Users//Igor//ProgProjects//JavaProjects//Lab1//output.txt");
            PrintStream printStream = new PrintStream(fos);

            try {
                FileReader fr = new FileReader(fIn);
                BufferedReader reader = new BufferedReader(fr);
                String value = reader.readLine();
                StringBuilder ads = new StringBuilder();
                Calculator tmp = new Calculator();
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
                            if(answer != null) {
                                if(!sub_sub_str[i].equals("")) {
                                    tmp.add(sub_sub_str[i].charAt(0));
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
                printStream.println(ads.toString());
                System.out.println(ads.toString());
                c.Calc = tmp;
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
                FileReader fr = new FileReader(fOut);
                BufferedReader reader = new BufferedReader(fr);
                String value = reader.readLine();
                StringBuilder ads = new StringBuilder();
                Calculator tmp = new Calculator();

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
                            if(answer != null) {
                                if(!answer.equals("")) {
                                    tmp.add(answer.charAt(0));
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
                c.Calc = tmp;
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
