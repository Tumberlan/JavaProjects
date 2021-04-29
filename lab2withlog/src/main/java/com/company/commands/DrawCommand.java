package com.company.commands;

import com.company.GameDraw;
import com.company.RobotCommands;
import com.company.RobotModes;

import java.io.IOException;

import com.company.RunLogGame;
import org.apache.log4j.Logger;

public class DrawCommand extends Command{

    private final static Logger logger = Logger.getLogger(DrawCommand.class);

    public DrawCommand(GameDraw w){
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
        this.GetWrld().SetRobotMode(RobotModes.ON);
        logger.info("DRAW command was done");
        this.GetWrld().PrintField();

    }

}
