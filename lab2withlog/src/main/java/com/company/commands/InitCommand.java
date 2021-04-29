package com.company.commands;

import com.company.GameDraw;
import org.apache.log4j.Logger;

import java.io.IOException;



public class InitCommand extends Command{

    private final static Logger logger = Logger.getLogger(InitCommand.class);

    public InitCommand(GameDraw w){
        super(w);
    }

@Override
    public boolean CheckCommand(){
        if(this.command.length != 5 ){
            System.out.println("Wrong number of arguments");
            return false;
        }
        if(Integer.parseInt(this.GetCommandLine()[1]) < 1 || Integer.parseInt(this.GetCommandLine()[2])<1 || Integer.parseInt(this.GetCommandLine()[3]) < 0 || Integer.parseInt(this.GetCommandLine()[4]) < 0){
            System.out.println("Wrong arguments");
            return false;
        }
        return true;
    }

    public void DoCommand() throws IOException {
    this.GetWrld().InitGame(Integer.parseInt(this.GetCommandLine()[1]),Integer.parseInt(this.GetCommandLine()[2]), Integer.parseInt(this.GetCommandLine()[3]),Integer.parseInt(this.GetCommandLine()[4]));
    logger.info("INIT command was done");
    this.GetWrld().PrintField();
}
}
