package com.company;

import java.io.*;
import java.util.Scanner;

public class GameDraw {

    private GameField field = new GameField();

    private RobotDrawer robot = new RobotDrawer();

   // private File outF = new File("C://Users//Igor//ProgProjects//JavaProjects//lab2", "out.txt");
    GameDraw() throws IOException {
    }

    public void PrintField() throws IOException {
     //   FileWriter outFstream = new FileWriter(outF, false);
        this.GetRobotFieldBalance();
        for(int i = 0; i < field.GetBorders().GetXCoord();i++){
            for(int j = 0; j < field.GetBorders().GetYCoord(); j++){
                if(i == robot.GetCoords().GetXCoord() & j == robot.GetCoords().GetYCoord()){
                    //outFstream.write("R ");
                    System.out.print("R ");
                }else if (field.IsDrawn(i,j)){
                    //outFstream.write("* ");
                    System.out.print("* ");
                }else{
                    //outFstream.write(". ");
                    System.out.print(". ");
                }
            }
            //outFstream.write('\n');
            System.out.print('\n');
        }
       // outFstream.close();
    }


    /**
     * this function make new coords be in field limits and make Robot and Field be correctly connected
     */
    public void GetRobotFieldBalance(){
        Coords coords = field.GetBalancedCoords(robot.GetCoords().GetXCoord(), robot.GetCoords().GetYCoord());
        robot.SetCoords(coords.GetXCoord(),coords.GetYCoord());
        if(field.IsDrawn(robot.GetCoords().GetXCoord(), robot.GetCoords().GetYCoord())) {
            field.SetState(robot.GetCoords().GetXCoord(), robot.GetCoords().GetYCoord(), true);
        }else{
            field.SetState(robot.GetCoords().GetXCoord(), robot.GetCoords().GetYCoord(), robot.GetBoolMode());
        }
    }

    /**
     *
     * @param w - field x limit
     * @param h - field y limit
     * @param a - robot x coord
     * @param b - robot y coord
     * ititialization of the game
     */
    public void InitGame(int w, int h, int a, int b){
        field.SetBorders(w,h);
        robot.SetCoords(a,b);
    }

    public void SetRobotMode(RobotModes mode){
        robot.SetMode(mode);
        if(mode == RobotModes.ON);
    }

    public RobotModes GetRobotMode(){
        return robot.GetMode();
    }

    public void MoveRobot(RobotCommands command, int amount)
    {

        for (int i = 0; i < amount; i++) {
            robot.Move(command);
            GetRobotFieldBalance();
        }

    }

    public void TeleportRobot(int h, int w){
        Coords coords = field.GetBalancedCoords(h,w);
        robot.SetCoords(coords.GetXCoord(),coords.GetYCoord());
    }

}
