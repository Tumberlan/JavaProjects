package com.company.commands;

import com.company.GameDraw;

import java.io.IOException;

public class Command {
    protected   GameDraw wrld;
    protected   String[] command;

    public GameDraw GetWrld(){
        return wrld;
    }
    public String[] GetCommandLine(){
        return command;
    }


    public Command(GameDraw w){
        wrld = w;
    }

    public void SetCommandLine(String[] cm){
        command = cm;
    }

    public boolean CheckCommand(){
        return true;
    }
    public void DoCommand() throws IOException {

    }
}
