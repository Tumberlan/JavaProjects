package com.company;

public class GameField {
    private int height;
    private int width;
    private boolean[] map;

    public GameField(){
        this.height = 0;
        this.width = 0;
        this.map = null;
    }

    /**
     *
     * @param x
     * @param y
     * @return Coords - special structer which contains X and Y parametr
     * if x and y are not in field limits, this function make them suitable for game field
     */
    public Coords GetBalancedCoords(int x, int y){
        while (x < 0){
            x += height;
        }
        while(x >= height){
            x -= height;
        }
        while(y < 0){
            y += width;
        }
        while(y >= width){
            y -= width;
        }
        return new Coords(x,y);
    }

    public void SetBorders(int h, int w){
        height = h;
        width = w;
        map = new boolean[height*width];
        for(int i = 0; i < height*width; i++){
            map[i] = false;
        }
    }

    public Coords GetBorders(){
        return new Coords(height,width);
    }

    /**
     *
     * @param x
     * @param y
     * @return id in array of gaame cells
     * all coords in game are in array not in matrix so this function give x and y their array id
     */
    public int GetCellIdx(int x, int y){
        Coords coords = GetBalancedCoords(x,y);
        return (coords.GetYCoord()*height + coords.GetXCoord());
    }

    public boolean IsDrawn(int x, int y){
        return map[GetCellIdx(x,y)];
    }

    public void SetState(int x, int y, boolean state){
        map[GetCellIdx(x,y)] = state;
    }
}
