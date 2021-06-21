package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

public class PlayerTwoTask implements Runnable {
    DataInputStream dis;
    DataOutputStream dos;

    public PlayerTwoTask(DataInputStream diStream, DataOutputStream doStream){
        dis = diStream;
        dos = doStream;
    }
    private void sendReceive() throws IOException {
        int[] tmp = new int[3];
        boolean done = false;
        for (int i = 0; i < 3; i++){
            tmp[i] = dis.readInt();
            if(tmp[i] != 0){
                done = true;
            }
        }
        boolean spammed = dis.readBoolean();
        if(done) {
            for (int i = 0; i < 3; i++) {
                dos.writeInt(tmp[i]);
            }
            dos.writeBoolean(spammed);
        }
    }

    public void run(){
        boolean isGo = true;
        while (isGo){
            try {
                sendReceive();
            }catch (IOException e){
                isGo = false;
            }
        }
    }
}
