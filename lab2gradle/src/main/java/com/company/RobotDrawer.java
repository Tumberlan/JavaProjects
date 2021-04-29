package com.company;

public class RobotDrawer {

    private int x;
    private int y;
    private RobotModes mode;

    public RobotDrawer(){
        mode = RobotModes.OFF;
    }

    public void SetMode(RobotModes value){
        this.mode = value;
    }

    public RobotModes GetMode(){
        return this.mode;
    }

    public boolean GetBoolMode(){
        if(GetMode() == RobotModes.ON){
            return true;
        }
        return false;
    }

    public Coords GetCoords(){
        return new Coords(x,y);
    }

    public void SetCoords(int a, int b){
        x = a;
        y = b;
    }

    public void Move(RobotCommands command){
        if (command == RobotCommands.UP){
            x--;
        }
        if (command == RobotCommands.RIGHT){
            y++;
        }
        if (command == RobotCommands.DOWN){
            x++;
        }
        if (command == RobotCommands.LEFT){
            y--;
        }
    }
}
