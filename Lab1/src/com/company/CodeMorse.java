package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class CodeMorse {

    File file;
    Map<String, String> CodeDictionary = new HashMap<String, String>();
    HashSet<String> CD = new HashSet<String>();
    Map<String, String> DecodeDictionary = new HashMap<String, String>();
    //Map<String, String> AmountDictionary = new HashMap<String, String>();
    Calculator calc = new Calculator();

    public CodeMorse(File f) {
        file = f;
    }


    public void FillDictionaries(){

        try {

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String value = reader.readLine();
            while (!value.equals("$")){
                String parse_symbol = " ";
                String[] sub_str = value.split(parse_symbol);

                CD.add(sub_str[0]);
                CD.hashCode();
                CodeDictionary.put(sub_str[0], sub_str[1]);
                DecodeDictionary.put(sub_str[1], sub_str[0]);
                //AmountDictionary.put(sub_str[0], "0");

                value = reader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
/*
    public void WriteAmount(){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            FileOutputStream fos = new FileOutputStream("C://Users//Igor//ProgProjects//JavaProjects//Lab1//amount.txt");
            PrintStream printStream = new PrintStream(fos);
            String value = reader.readLine();
            while (!value.equals("$")){

                String parse_symbol = " ";
                String[] sub_str = value.split(parse_symbol);
                StringBuilder ads = new StringBuilder();
                int counter = Integer.parseInt(AmountDictionary.get(sub_str[0]));

                if(counter > 0) {
                    ads.append(sub_str[0]).append(" ").append(counter);
                    printStream.println(ads.toString());
                    System.out.println(ads.toString());
                }
                value = reader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
*/

}
