package com.company;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.Scanner;

public class RunLogGame {

    private final static Logger logger = Logger.getLogger(RunLogGame.class);

    private GameDraw wrld;
    private CommandParser parser;
    public RunLogGame() throws IOException {
        try {
            logger.info("Log Game start ...");
            wrld = new GameDraw();
            parser = new CommandParser(wrld);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean TakeCommRes(String comm){
        return parser.AnalyzeCommand(comm);
    }
    public void RunGame(){
        Scanner in = new Scanner(System.in);
        String comm = in.nextLine();
        while (!comm.equals("end")) {
            TakeCommRes(comm);
            comm = in.nextLine();
        }
    }


}
