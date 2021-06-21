package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerPlayerOne extends Thread{
    RoadLogic roadLogic;
    DataInputStream dis;
    DataOutputStream dos;

    public ServerPlayerOne(RoadLogic RL, DataInputStream diStream, DataOutputStream doStream){
        roadLogic = RL;
        dis = diStream;
        dos = doStream;
    }


    private void sendReceive() throws IOException {
        int[] tmp = roadLogic.player.GiveChanges();
        for(int i = 0; i < 6;i++){
            tmp[i] = dis.readInt();
        }
        for(int i = 0; i < 6;i++){
            dos.writeInt(tmp[i]);
        }
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
                sendReceive();
            }catch (IOException e){
                isGo = false;
            }
        }
    }
}