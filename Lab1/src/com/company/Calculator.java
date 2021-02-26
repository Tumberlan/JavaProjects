package com.company;

import java.io.*;
import java.util.*;

public class Calculator{

    private final Set<CharStat> stats = new HashSet<CharStat>();

    public Calculator(){}

    public void add(Character c){
        stats.add(new CharStat(c));
    }

    public void WriteAmount(){
        try {
            FileOutputStream fos = new FileOutputStream("C://Users//Igor//ProgProjects//JavaProjects//Lab1//amount.txt");
            PrintStream printStream = new PrintStream(fos);
            List<CharStat> sortedStats = new ArrayList<CharStat>(stats);
            Collections.sort(sortedStats);
            for(CharStat stat: sortedStats){
                printStream.println(stat.value.toString() + " " + stat.amount);
                System.out.println(stat.value.toString() + " " + stat.amount);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
