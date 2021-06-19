package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class RoadLogic implements Runnable {
    CarIntersection CI = new CarIntersection();
    public final int NORMAL_SPEED = 30;
    CarsDictionary cars = new CarsDictionary();
    String carNameStr = "SUBARU";
    Car tmp_car = cars.dictionary.get(carNameStr);
    Player p = new Player(tmp_car);
    Thread EnemiesFactory = new Thread(this);

    //Thread Audio = new Thread(new AudioThread());

    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    ArrayList<Player> playerList = new ArrayList<Player>();


    public RoadLogic(){
        playerList.add(p);
        EnemiesFactory.start();
        //  Audio.start();
    }

    public void FixEnemyCollisions(){
        Random random = new Random();
        for (Enemy enemy:enemyList){
            boolean check = true;
            for(Enemy enemy1:enemyList){
                if(enemy != enemy1){
                    if(CI.CarIntersectionQ(enemy,enemy1,true)){
                        if(enemy.x < enemy1.x){
                            if(enemy.speed - enemy1.speed > 0){
                                enemy.speed = enemy1.speed-1;
                                if(enemy.speed < enemy.MIN_SPEED){
                                    enemy.speed = enemy.MIN_SPEED;
                                }
                                enemy.forward_block = true;
                                enemy.forward_block_fixer = 40;
                            }
                        }else{
                            if(enemy1.speed - enemy.speed > 0){
                                enemy1.speed = enemy.speed-1;
                                if(enemy1.speed < enemy1.MIN_SPEED){
                                    enemy1.speed = enemy1.MIN_SPEED;
                                }
                                enemy1.forward_block  = true;
                                enemy1.forward_block_fixer = 40;
                            }
                        }
                        check = false;
                    }
                }
            }

            for(Player player:playerList){
                if(CI.CarIntersectionQ(player,enemy,true)){
                    if(enemy.x < player.x){
                        if(enemy.speed - player.speed > 0){
                            if((enemy.speed/6) < player.speed || enemy.speed < 18){
                                enemy.speed = player.speed - 1;
                            }
                            if(enemy.speed < enemy.MIN_SPEED){
                                enemy.speed = enemy.MIN_SPEED;
                            }
                            enemy.forward_block = true;
                            enemy.forward_block_fixer = 40;
                        }
                        check = false;
                    }
                }
            }
            if(check) {
                if (enemy.forward_block_fixer > 0 && enemy.forward_block) {
                    enemy.forward_block_fixer--;
                    enemy.wait_for_boost_time = 100;
                } else {
                    enemy.forward_block = false;
                    if (enemy.speed < 5) {
                        enemy.speed = NORMAL_SPEED + random.nextInt(5);
                    } else {
                        if (enemy.wait_for_boost_time > 0) {
                            enemy.wait_for_boost_time--;
                        }
                        if (enemy.wait_for_boost_time == 0) {
                            enemy.speed += 1;
                            if (enemy.speed > enemy.MAX_WANTED_SPEED) {
                                enemy.speed = enemy.MAX_WANTED_SPEED;
                            }
                            enemy.wait_for_boost_time = 300;
                        }
                    }
                }
            }
        }

    }

    public void TmpRoadChanges(){
        GetPriority(p);
        Iterator<Enemy> iterator = enemyList.iterator();
        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            if(enemy.x >= 2400 || enemy.x <= -2400){
                iterator.remove();
            }else {
                GetPriority(enemy);
            }
        }
    }

    private void MoveEnemies(){
        Iterator<Enemy> iterator = enemyList.iterator();
        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            enemy.move();
        }
    }


    private boolean TestCollisionsWithEnemies(){
        Iterator<Enemy> iterator = enemyList.iterator();
        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            if(CI.CarIntersectionQ(p,enemy,false)){
                return true;
            }
        }
        return false;
    }

    public String getEnemyCar(Player p){
        Random random = new Random();
        int number = random.nextInt(8
        );
        switch (number) {
            case 0 -> {
                String tmp = "SUBARU";
                if(carNameStr.equals(tmp)){
                    return "VOLKSWAGEN_POLO";
                }
                return "SUBARU";
            }
            case 1 -> {
                String tmp = "KIA";
                if(carNameStr.equals(tmp)){
                    return "VOLKSWAGEN_POLO";
                }
                return "KIA";
            }
            case 2 -> {
                String tmp = "LAND_CRUISER";
                if(carNameStr.equals(tmp)){
                    return "VOLKSWAGEN_POLO";
                }
                return "LAND_CRUISER";
            }
            case 3 -> {
                String tmp = "BLACK_LAND_CRUISER";
                if(carNameStr.equals(tmp)){
                    return "VOLKSWAGEN_POLO";
                }
                return "BLACK_LAND_CRUISER";
            }
            case 4 -> {
                String tmp = "FORD_FOCUS";
                if(carNameStr.equals(tmp)){
                    return "VOLKSWAGEN_POLO";
                }
                return "FORD_FOCUS";
            }
            case 5 -> {
                String tmp = "BENZ_TRUCK";
                if(carNameStr.equals(tmp)){
                    return "VOLKSWAGEN_POLO";
                }
                return "BENZ_TRUCK";
            }
            case 6 -> {
                String tmp = "BUS";
                if(carNameStr.equals(tmp)){
                    return "VOLKSWAGEN_POLO";
                }
                return "BUS";
            }
            case 7 -> {
                String tmp = "ROOT_TAXI";
                if(carNameStr.equals(tmp)){
                    return "VOLKSWAGEN_POLO";
                }
                return "ROOT_TAXI";
            }
        }
        return null;
    }

    public int GetEnemyY(Car enemyCar){
        Random random = new Random();

        int number = random.nextInt(3);
        switch (number){
            case 0->{
                return enemyCar.MAX_TOP + random.nextInt(enemyCar.random_number_top);
            }
            case 1->{
                return enemyCar.MAX_TOP+120 + random.nextInt(enemyCar.random_number);
            }
            case 2->{
                return enemyCar.MAX_TOP+270 + random.nextInt(enemyCar.random_number);
            }
        }
        return 0;
    }

    public void GetPriority(Car car){
        car.priority = car.y + car.height;
    }


    public void RoadLive(){
        p.move();
        MoveEnemies();
        FixEnemyCollisions();
        TestCollisionsWithEnemies();
    }


    public void run(){
        while (true){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(4000)+1000);
                Car enemy_car = cars.dictionary.get(getEnemyCar(p));
                enemyList.add(new Enemy(enemy_car,2000, GetEnemyY(enemy_car),
                        random.nextInt(20)+20,this));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}