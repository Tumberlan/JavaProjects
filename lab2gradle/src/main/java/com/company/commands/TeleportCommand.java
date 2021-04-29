package com.company.commands;

import com.company.GameDraw;
import org.apache.log4j.Logger;
import java.io.IOException;

public class TeleportCommand extends Command{

    private final static Logger logger = Logger.getLogger(TeleportCommand.class);

    public TeleportCommand(GameDraw w){
        super(w);
    }
@Override
    public boolean CheckCommand(){
        if(this.command.length != 3){
            System.out.println("Wrong number of arguments");
            return false;
        }
        if(Integer.parseInt(this.GetCommandLine()[1]) < 0 || Integer.parseInt(this.GetCommandLine()[2]) < 0){
            System.out.println("Wrong arguments");
            return false;
        }
        return true;
    }

    public void DoCommand() throws IOException {
        this.GetWrld().TeleportRobot(Integer.parseInt(this.GetCommandLine()[1]), Integer.parseInt(GetCommandLine()[2]));
        logger.info("TELEPORT command was done");
        this.GetWrld().PrintField();
    }
}
