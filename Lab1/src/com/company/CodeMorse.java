package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class CodeMorse {

    Map<String, String> CodeDictionary = new HashMap<String, String>();
    Map<String, String> DecodeDictionary = new HashMap<String, String>();
    Map<String, String> AmountDictionary = new HashMap<String, String>();
    public void FillDictionaries(){

        try {
            File file = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "codes.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            for (int i = 0; i < 53; i++) {
                String value = reader.readLine();
                String parse_symbol = " ";
                String[] sub_str = value.split(parse_symbol);

                CodeDictionary.put(sub_str[0], sub_str[1]);
                DecodeDictionary.put(sub_str[1], sub_str[0]);
                AmountDictionary.put(sub_str[0], "0");

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void WriteAmount(){
        try {
            File file = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "codes.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            FileOutputStream fos = new FileOutputStream("C://Users//Igor//ProgProjects//JavaProjects//Lab1//amount.txt");
            PrintStream printStream = new PrintStream(fos);
            for (int i = 0; i < 52; i++) {
                String value = reader.readLine();
                String parse_symbol = " ";
                String[] sub_str = value.split(parse_symbol);
                StringBuilder ads = new StringBuilder();
                int counter = Integer.parseInt(AmountDictionary.get(sub_str[0]));

                if(counter > 0) {
                    ads.append(sub_str[0]).append(" ").append(counter);
                    printStream.println(ads.toString());
                    System.out.println(ads.toString());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
