package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Multiplayer extends Thread{
    private String ip = "localHost";
    private int port = 22222;
    private ArrayList<Socket> socket = new ArrayList<Socket>();
    private ServerSocket serverSocket;
    private ArrayList<DataOutputStream> dos = new ArrayList<DataOutputStream>();
    private ArrayList<DataInputStream> dis = new ArrayList<DataInputStream>();
    private boolean isConnected = false;
    private boolean isServer = false;
    private int numberOfConnectedPlayers = 0;
    private RoadLogic roadLogic;
    private String carName;
    private int myId;

    public void InitServer(String new_ip, int new_port){
        ip = new_ip;
        port = new_port;
        try{
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            myId = 0;
            requestListener = new RequestListener();
            requestListener.start();
            serverListener = new ServerListener();
            serverListener.start();
            serverWriter = new ServerWriter();
            serverWriter.start();
            System.out.println("Server started");
            isServer = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class PlayerOneSend extends Thread{
        private void send() throws IOException {
            dos.get(0).write(roadLogic.player.GiveChanges());
        }
    }


}
