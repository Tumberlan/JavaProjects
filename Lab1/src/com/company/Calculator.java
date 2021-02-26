package com.company;

import java.io.*;
import java.util.*;

public class Calculator{

    private final Set<CharStat> stats = new HashSet<CharStat>();

    public Calculator(){}

    public void add(Character C){
        stats.add(new CharStat(C));
    }

    public final void WriteAmount(){
        try {
            FileOutputStream fos = new FileOutputStream("C://Users//Igor//ProgProjects//JavaProjects//Lab1//amount.txt");
            PrintStream printStream = new PrintStream(fos);
            List<CharStat> SortedStats = new ArrayList<CharStat>(stats);
            Collections.sort(SortedStats);
            for(CharStat stat: SortedStats){
                printStream.println(stat.Value.toString() + " " + stat.Amount);
                System.out.println(stat.Value.toString() + " " + stat.Amount);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
