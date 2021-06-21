package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerTwoReceive extends Thread {
    RoadLogic roadLogic;
    DataInputStream dis;
    DataOutputStream dos;

    public PlayerTwoReceive(RoadLogic RL, DataInputStream diStream, DataOutputStream doStream){
        roadLogic = RL;
        dis = diStream;
        dos = doStream;
    }
    private void recieve() throws IOException {
        int[] tmp = new int[6];
        for(int i = 0; i < 6; i++){
            tmp[i] = dis.readInt();
        }
        roadLogic.player.x = tmp[0];
        roadLogic.player.y = tmp[1];
        roadLogic.player.dx = tmp[2];
        roadLogic.player.dy = tmp[3];
        roadLogic.player.conditionIdx = tmp[4];
        roadLogic.player.acceleration = tmp[5];
        roadLogic.changePlayerImg(roadLogic.player.conditionIdx);
    }

    public void run(){
        boolean isGo = true;
        while (isGo){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                recieve();
            } catch (IOException e) {
                isGo = false;
            }
        }
    }
}
