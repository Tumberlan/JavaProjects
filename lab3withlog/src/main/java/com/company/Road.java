package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {
    Image img = new ImageIcon("src/res/game_road.png").getImage();

    Timer mainTimer = new Timer(40, this);
    CarsDictionary cars = new CarsDictionary();
    String carNameStr = "FORD_FOCUS";
    Car tmp_car = cars.dictionary.get(carNameStr);
    Player player = new Player(tmp_car);

    Thread EnemiesFactory = new Thread(this);

    //Thread Audio = new Thread(new AudioThread());

    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();



    public Road(){
        mainTimer.start();
        EnemiesFactory.start();
      //  Audio.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    private class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent event){
            player.keyPressed(event);
        }
        public void keyReleased(KeyEvent event){
            player.keyReleased(event);
        }
    }


    public void paint(Graphics g){
        g = (Graphics2D)g;
        g.drawImage(img,player.layer1,0,null);
        g.drawImage(img,player.layer2, 0, null);
        g.drawImage(player.image, player.x, player.y, null);
        double player_v = ( 200/(player.MAX_SPEED*2)) * player.speed * 2;
        Font font = new Font("Times New Roman",Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Speed: " + player_v + " km/h", 50, 30);
        Iterator<Enemy> iterator = enemyList.iterator();
        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            if(enemy.x >= 2400 || enemy.x <= -2400){
                iterator.remove();
            }else {
                g.drawImage(enemy.image, enemy.x, enemy.y, null);
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


    private void TestCollisionsWithEnemies(){
        Iterator<Enemy> iterator = enemyList.iterator();
        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            if(player.getRect().intersects(enemy.getRect())){
                JOptionPane.showMessageDialog(null, "YOU LOST");
                System.exit(1);
            }
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        MoveEnemies();
        repaint();
        TestCollisionsWithEnemies();
    }

    public void run(){
        while (true){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000)+2000);
                Car enemy_car = cars.dictionary.get(getEnemyCar(player));
                enemyList.add(new Enemy(enemy_car,1600, GetEnemyY(enemy_car),
                        random.nextInt(20)+20,this));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
