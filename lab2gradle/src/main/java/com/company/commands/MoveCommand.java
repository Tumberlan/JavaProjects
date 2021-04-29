package com.company.commands;

import com.company.GameDraw;
import com.company.RobotCommands;
import org.apache.log4j.Logger;

import java.io.IOException;

public class MoveCommand extends Command{
    private final static Logger logger = Logger.getLogger(MoveCommand.class);
    public MoveCommand(GameDraw w){
        super(w);
    }

    public RobotCommands SwitchType(String str){
        if(str.equals("U")){
            return RobotCommands.UP;
        }else if(str.equals("D")){
            return RobotCommands.DOWN;
        }else if(str.equals("L")){
            return RobotCommands.LEFT;
        }else if(str.equals("R")){
            return RobotCommands.RIGHT;
        }else{
            return null;
        }
    }
@Override

    public boolean CheckCommand(){
        if(this.command.length != 3){
            System.out.println("Wrong number of arguments");
            return false;
        }
        if(SwitchType(this.GetCommandLine()[1]) == null || Integer.parseInt(this.GetCommandLine()[2]) < 0){
            System.out.println("Wrong arguments");
            return false;
        }
        return true;
    }
    public void DoCommand() throws IOException {
        this.GetWrld().MoveRobot(SwitchType(this.GetCommandLine()[1]), Integer.parseInt(this.GetCommandLine()[2]));
        logger.info("MOVE command was done");
        this.GetWrld().PrintField();
    }
}
