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
        boolean done = false;
        for(int i = 0; i < 6;i++){
            tmp[i] = dis.readInt();
            if(tmp[i] != 0) {
                done = true;
            }
        }
        if(done) {
            for (int i = 0; i < 6; i++) {
                dos.writeInt(tmp[i]);
            }
        }
    }

    public Boolean call(){
        boolean isGo = true;
        while (isGo){
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
