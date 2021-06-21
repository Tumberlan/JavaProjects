package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class PlayerOneTask implements Callable<Boolean> {

    DataInputStream dis;
    DataOutputStream dos;
    ArrayList<Boolean> readyList;
    int ownNumber;

    public PlayerOneTask(DataInputStream diStream, DataOutputStream doStream, ArrayList<Boolean> rlst,int number){
        dis = diStream;
        dos = doStream;
        readyList = rlst;
        ownNumber = number;
    }


    private void sendReceive() throws IOException {
        int[] tmp = new int[6];
        for(int i = 0; i < 6;i++){
            tmp[i] = dis.readInt();
        }
        for(int i = 0; i < 6;i++){
            dos.writeInt(tmp[i]);
        }
    }

    public Boolean call(){
        boolean isGo = false;
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
        readyList.set(ownNumber, true);
        return true;
    }
}
