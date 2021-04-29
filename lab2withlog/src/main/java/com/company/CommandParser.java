package com.company;

import java.io.IOException;


public class CommandParser {
    private final CommandFabric fabric;

    public CommandParser(GameDraw wrld) throws IOException {
        fabric = new CommandFabric(wrld);
    }

    /**
     *
     * @param comm
     * function for taking and checking input
     */
    public boolean AnalyzeCommand(String comm){
        String[] sub_str = comm.split(" ");
        if(sub_str.length == 0){
            System.out.println("Empty command string");
            return false;
        }else if(fabric.CD.dictionary.get(sub_str[0]) == null){
            System.out.println("Wrong command name");
            return false;
        }else {
            return fabric.CreateCommand(comm);
        }
    }

}
