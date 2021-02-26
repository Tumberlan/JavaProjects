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
    Calculator Calc = new Calculator();

    public CodeMorse(File f) {
        file = f;
    }


    public void FillDictionaries(){

        try {

            FileReader FR = new FileReader(file);
            BufferedReader Reader = new BufferedReader(FR);
            String Value = Reader.readLine();
            while (!Value.equals("$")){
                String ParseSymbol = " ";
                String[] SubStr = Value.split(ParseSymbol);

                CD.add(SubStr[0]);
                CD.hashCode();
                CodeDictionary.put(SubStr[0], SubStr[1]);
                DecodeDictionary.put(SubStr[1], SubStr[0]);

                Value = Reader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
