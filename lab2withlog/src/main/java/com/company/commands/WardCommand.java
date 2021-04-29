package com.company.commands;

import com.company.GameDraw;
import com.company.RobotModes;
import org.apache.log4j.Logger;

import java.io.IOException;

public class WardCommand extends Command{

    private final static Logger logger = Logger.getLogger(WardCommand.class);

    public WardCommand(GameDraw w){
        super(w);
    }
@Override
    public boolean CheckCommand(){
        if(this.command.length != 1){
           System.out.println("Wrong number of arguments");
           return false;
        }
        return true;
    }
    public void DoCommand() throws IOException {
        this.GetWrld().SetRobotMode(RobotModes.OFF);
        logger.info("WARD command was done");
        this.GetWrld().PrintField();
    }
}
