package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

public class PlayerTwoTask implements Callable<Boolean> {
    DataInputStream dis;
    DataOutputStream dos;

    public PlayerTwoTask(DataInputStream diStream, DataOutputStream doStream){
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

    public Boolean call(){
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
        return true;
    }
}
