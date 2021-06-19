package com.company;

import java.awt.event.KeyEvent;

public class Player2 {
    String carName = "FORD_FOCUS";
    int carNameIdx = 0;
    int lineNumber = 1;
    boolean spamCar = false;

    public void getCNbyCNI(){
        switch (carNameIdx){
            case 0 ->{
                carName = "SUBARU";
            }
            case 1 ->{
                carName = "KIA";
            }
            case 2 ->{
                carName = "LAND_CRUISER";
            }
            case 3 ->{
                carName = "BLACK_LAND_CRUISER";
            }
            case 4 ->{
                carName = "FORD_FOCUS";
            }
            case 5 ->{
                carName = "BENZ_TRUCK";
            }
            case 6 ->{
                carName = "BUS";
            }
            case 7 ->{
                carName = "ROOT_TAXI";
            }
            case 8 -> {
                carName = "TRUCK";
            }
        }
    }
    public void increaseCNI(){
        carNameIdx++;
        if(carNameIdx > 8){
            carNameIdx = 0;
        }
        getCNbyCNI();
    }
    public void decreaseCNI(){
        carNameIdx--;
        if(carNameIdx < 0){
            carNameIdx = 8;
        }
        getCNbyCNI();
    }
    public void increaseLN(){
        lineNumber++;
        if(lineNumber > 3){
            lineNumber = 1;
        }
    }
    public void decreaseLN(){
        lineNumber--;
        if(lineNumber < 1){
            lineNumber = 3;
        }
    }


    public void keyTyped(KeyEvent event){
        int key_code = event.getKeyCode();
        if(key_code == KeyEvent.VK_D){
            increaseCNI();
        }
        if(key_code == KeyEvent.VK_A){
            decreaseCNI();
        }
        if(key_code == KeyEvent.VK_S){
            increaseLN();
        }
        if(key_code == KeyEvent.VK_W){
            decreaseLN();
        }
        if(key_code == KeyEvent.VK_SPACE){
            spamCar = true;
        }
    }
}
