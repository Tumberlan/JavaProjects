package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class CodeMorse {

    Map<String, String> CodeDictionary = new HashMap<String, String>();
    Map<String, String> DecodeDictionary = new HashMap<String, String>();

    public void FillDictionaries(){

        try {
            File file = new File("C://Users//Igor//ProgProjects//JavaProjects//Lab1", "codes.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            for (int i = 0; i < 36; i++) {
                String value = reader.readLine();
                String parse_symbol = " ";
                String[] sub_str = value.split(parse_symbol);

                CodeDictionary.put(sub_str[0], sub_str[1]);
                DecodeDictionary.put(sub_str[1], sub_str[0]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
