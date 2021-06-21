package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class Multiplayer extends Thread{
    private String ip = "localHost";
    private int port = 22222;
    private Socket socket;
    private ServerSocket serverSocket;
    private boolean isConnected = false;
    private boolean isServer = false;
    private RoadLogic roadLogic = new RoadLogic(true);
    DataInputStream dis;
    DataOutputStream dos;
    PlayerOneSend playerOneSend;
    PlayerTwoSend playerTwoSend;
    PlayerOneReceive playerOneReceive;
    PlayerTwoReceive playerTwoReceive;

    public void StartServer(String new_ip, int new_port){
        ip = new_ip;
        port = new_port;
        try{
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            MyExecutor myExecutor = new MyExecutor(serverSocket);
            myExecutor.runExecutor();
            System.out.println("Server started");
            isServer = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public void InitServer(String new_ip, int new_port,String name){
        ip = new_ip;
        port = new_port;
        try{

            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            playerOneSend = new PlayerOneSend(roadLogic,dis,dos,name);
            playerOneSend.start();
            playerOneReceive = new PlayerOneReceive(roadLogic,dis,dos);
            playerOneReceive.start();
            System.out.println("Server started");
            isServer = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    public void connectSpamer(String new_ip, int new_port, String name){
        ip = new_ip;
        port = new_port;
        try{
            socket=new Socket(ip,port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            playerTwoSend = new PlayerTwoSend(roadLogic,dis,dos,name);
            playerTwoSend.start();
            playerTwoReceive = new PlayerTwoReceive(roadLogic,dis,dos);
            playerTwoReceive.start();
            isConnected = true;
            System.out.println("Successfully connected to the server");
        } catch (IOException e) {
            System.out.println("Unable to connect to the server");
        }
    }

    public void connectDriver(String new_ip, int new_port, String name){
        ip = new_ip;
        port = new_port;
        try{
            socket = new Socket(ip,port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            playerTwoSend = new PlayerTwoSend(roadLogic,dis,dos,name);
            playerTwoSend.start();
            playerTwoReceive = new PlayerTwoReceive(roadLogic,dis,dos);
            playerTwoReceive.start();
            isConnected = true;
            System.out.println("Successfully connected to the server");
        } catch (IOException e) {
            System.out.println("Unable to connect to the server");
        }
    }


  /*  public class ThreadPool implements Executor {
        private final Queue<Runnable> playerOneQueue = new ConcurrentLinkedQueue<>();
        private final Queue<Runnable> playerTwoQueue = new ConcurrentLinkedQueue<>();
        private volatile boolean isRunning = false;

        public ThreadPool(int nThreads){
            for (int i =0; i < nThreads; i++){
                new Thread(new TaskWorker()).start();
            }
        }

        @Override
        public void execute(Runnable command){
            if(isRunning){
                if()
                playerOneQueue.offer(command);
                playerTwoQueue.offer(command);
            }
        }

        public void shutdown() {
            isRunning = false;
        }

        private final class TaskWorker implements Runnable{
            @Override
            public void run(){
                while (isRunning){
                    Runnable pFirstNeed = playerOneQueue.poll();
                    Runnable pSecondNeed = playerTwoQueue.poll();
                    if(pFirstNeed != null && pSecondNeed != null){
                        pFirstNeed.run();
                        pSecondNeed.run();
                    }
                }
            }
        }
    }

    public class ServerPlayerOne extends Thread{
        private void sendReceive() throws IOException {
            int[] tmp = roadLogic.player.GiveChanges();
            for(int i = 0; i < 6;i++){
                dos.writeInt(tmp[i]);
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


    public class ServerPlayerTwo extends Thread{
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

    public class PlayerOneSend extends Thread{
        String pName;
        private PlayerOneSend(String name){
            pName = name;
        }
        private void send() throws IOException {
            int[] tmp = roadLogic.player.GiveChanges();
            for(int i = 0; i < 6;i++){
                dos.writeInt(tmp[i]);
            }

        }
        public void run(){
            boolean isGo = true;
            RaceLayout RL = new RaceLayout();
            RL.StartLayout(1, roadLogic, pName);
            while (isGo){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    send();
                }catch (IOException e){
                    roadLogic.deactivateFabric = false;
                    roadLogic.isP2Active = false;
                    isGo = false;
                }
            }
        }
    }

    public class PlayerTwoSend extends Thread{
        String pName;
        private PlayerTwoSend(String name){
            pName = name;
        }
        private void send() throws IOException {
            int[] tmp = roadLogic.player2.GiveChanges();
            for(int i = 0; i < 3;i++){
                dos.writeInt(tmp[i]);
            }
            dos.writeBoolean(roadLogic.player2.carWasSpammed);
            roadLogic.player2.carWasSpammed = false;
        }

        public void run(){
            RaceLayout RL = new RaceLayout();
            RL.StartLayout(2,roadLogic, pName);
            boolean isGo = true;
            while (isGo){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    send();
                } catch (IOException e) {
                    isGo = false;
                }
            }
        }
    }

    public class PlayerOneRecieve extends Thread{
        private void recieve() throws IOException{
            int[] tmp = new int[3];
            for (int i = 0; i < 3; i++){
                tmp[i] = dis.readInt();
            }
            boolean spammed = dis.readBoolean();
            roadLogic.index = tmp[0];
            roadLogic.speed = tmp[1];
            roadLogic.enY = tmp[2];
            roadLogic.spammer = spammed;

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
                    roadLogic.deactivateFabric = false;
                    roadLogic.isP2Active = false;
                    isGo = false;
                }
            }
        }
    }

    public class PlayerTwoRecieve extends Thread{
        private void recieve() throws IOException{
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
*/
}
