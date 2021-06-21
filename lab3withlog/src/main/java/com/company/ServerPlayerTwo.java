package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerPlayerTwo extends Thread{
    RoadLogic roadLogic;
    DataInputStream dis;
    DataOutputStream dos;

    public ServerPlayerTwo(RoadLogic RL, DataInputStream diStream, DataOutputStream doStream){
        roadLogic = RL;
        dis = diStream;
        dos = doStream;
    }
    private void sendReceive() throws IOException {
        int[] tmp = new int[3];
        for (int i = 0; i < 3; i++){
            tmp[i] = dis.readInt();
        }
        boolean spammed = dis.readBoolean();
        for(int i = 0; i < 3;i++){
            dos.writeInt(tmp[i]);
        }
        dos.writeBoolean(spammed);
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
