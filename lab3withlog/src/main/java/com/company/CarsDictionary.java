package com.company;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CarsDictionary {
    public Map<String, Car> dictionary = new HashMap<String, Car>();
    public File file = new File("C:\\Users\\Igor\\ProgProjects\\JavaProjects\\lab3withlog\\src\\res", "ALL_CARS_PATH.txt");

    public CarsDictionary(){
        ParseCarsStats();
    }
    public void ParseCarsStats(){
        try{
            FileReader FR = new FileReader(file);
            BufferedReader reader = new BufferedReader(FR);
            String value = reader.readLine();
            while(!value.equals("$")){
                String parse_symbol = "=";
                String[] sub_str = value.split(parse_symbol);
                String parse_stat_symbol = ",";
                String[] sub_stat_str = sub_str[1].split(parse_stat_symbol);
                Car new_car = new Car(Integer.parseInt(sub_stat_str[0]),Integer.parseInt(sub_stat_str[1]),
                        new ImageIcon(sub_stat_str[2]).getImage(),new ImageIcon(sub_stat_str[3]).getImage(),
                        new ImageIcon(sub_stat_str[4]).getImage(),Integer.parseInt(sub_stat_str[5]),
                        Integer.parseInt(sub_stat_str[6]), Integer.parseInt(sub_stat_str[7]),
                        Integer.parseInt(sub_stat_str[8]),Integer.parseInt(sub_stat_str[9]),
                        Integer.parseInt(sub_stat_str[10]));
                dictionary.put(sub_str[0], new_car);
                value = reader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
