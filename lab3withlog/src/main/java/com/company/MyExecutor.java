package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

public class MyExecutor {
    ExecutorService pOneService = Executors.newFixedThreadPool(25);
    ExecutorService pTwoService = Executors.newFixedThreadPool(25);
    private static final int UNKNOWN = 0;
    private static final int DRIVER = 1;
    private static final int SPAMER = 2;

    ServerSocket serverSocket;
    ArrayList<MySocket> sockets = new ArrayList<MySocket>();
    ArrayList<DataInputStream> disS = new ArrayList<DataInputStream>();
    ArrayList<DataOutputStream> dosS = new ArrayList<DataOutputStream>();
    int connectedPlayers = 0;
    ArrayList<Integer> POneNumbers = new ArrayList<Integer>();
    ArrayList<Integer> PTwoNumbers = new ArrayList<Integer>();
    int connectedPOnes = 0;
    int connectedPTwos = 0;
    Queue<Integer> POneQueue = new LinkedList<Integer>();
    Queue<Integer> PTwoQueue = new LinkedList<Integer>();
    ArrayList<Future> futureList = new ArrayList<Future>();
    ArrayList<Boolean> readyList = new ArrayList<Boolean>();

    public void WhichIsReady(){
        Iterator<Boolean> iterator = readyList.iterator();
        while(iterator.hasNext()){
            Boolean value = iterator.next();
            if(value){
                iterator.remove();
                return;
            }
        }
    }

    public MyExecutor(ServerSocket SS){
        serverSocket = SS;
    }
    public void runExecutor() throws IOException, ExecutionException, InterruptedException {

        RequestManager requestManager = new RequestManager(serverSocket);
        requestManager.start();
        TaskManager taskManager = new TaskManager();
        taskManager.start();

    }

    private class RequestManager extends Thread{
        ServerSocket serverSocket;
        private RequestManager(ServerSocket sS){
            serverSocket = sS;
        }
        public void run(){
            while (true){
                try {
                    if(connectedPOnes == 25){
                        WhichIsReady();
                        connectedPOnes--;
                    }
                    Socket tmpSocket = serverSocket.accept();
                    sockets.add(new MySocket(UNKNOWN,tmpSocket));
                    DataInputStream disTmp = new DataInputStream(sockets.get(connectedPlayers).socket.getInputStream());
                    DataOutputStream dosTmp = new DataOutputStream(sockets.get(connectedPlayers).socket.getOutputStream());
                    boolean isDriver = disTmp.readBoolean();
                    if(isDriver){
                        sockets.set(connectedPlayers,new MySocket(DRIVER,sockets.get(connectedPlayers).socket));
                        POneNumbers.add(connectedPlayers);
                        POneQueue.add(connectedPlayers);
                        readyList.add(false);
                        connectedPOnes++;
                    }else{
                        sockets.set(connectedPlayers,new MySocket(SPAMER,sockets.get(connectedPlayers).socket));
                        PTwoNumbers.add(connectedPlayers);
                        PTwoQueue.add(connectedPlayers);
                        connectedPTwos++;
                    }
                    disS.add(disTmp);
                    dosS.add(dosTmp);
                    connectedPlayers++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class TaskManager extends Thread{
        public void run(){
            while(true){
                if(POneQueue.element() != null && PTwoQueue.element() != null){
                    int pOneIdx = POneQueue.remove();
                    int pTwoIdx = PTwoQueue.remove();
                    PlayerOneTask playerOneTask = new PlayerOneTask(disS.get(pOneIdx),
                            dosS.get(pOneIdx), readyList, pOneIdx);
                    PlayerTwoTask playerTwoTask = new PlayerTwoTask(disS.get(pTwoIdx),
                            dosS.get(pTwoIdx));
                    Future<Boolean> fTaskPOne = pOneService.submit(playerOneTask);
                    Future<Boolean> fTaskPTwo = pTwoService.submit(playerTwoTask);
                    futureList.add(fTaskPOne);
                }
            }
        }
    }

}
