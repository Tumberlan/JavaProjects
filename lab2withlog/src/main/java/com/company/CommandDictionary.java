package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class CommandDictionary {
    private final static Logger logger = Logger.getLogger(CommandDictionary.class);
    public Map<String,String> dictionary = new HashMap<String, String>();
    public File file = new File("C:\\Users\\Igor\\ProgProjects\\JavaProjects\\lab2withlog\\src\\main\\resources", "CommandsEq.txt");

    /**
     * function of making special map of path to each command type class and command type names
     */
    public void ParseCommands(){
        try{
            FileReader FR = new FileReader(file);
            BufferedReader reader = new BufferedReader(FR);
            String value = reader.readLine();
            logger.info("file was opened");
            while(!value.equals("$")){
                String parse_symbol = " ";
                String[] sub_str = value.split(parse_symbol);
                dictionary.put(sub_str[0], sub_str[1]);
                value = reader.readLine();
            }
            logger.info("all commands were parsed");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
