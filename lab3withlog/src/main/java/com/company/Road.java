package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Road extends JPanel implements ActionListener{
    Image img = new ImageIcon("src/res/game_road.png").getImage();
    RoadLogic RL = new RoadLogic();
    Timer mainTimer = new Timer(40, this);

    public Road(){
        mainTimer.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    private class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent event){
            RL.p.keyPressed(event);
        }
        public void keyReleased(KeyEvent event){
            RL.p.keyReleased(event);
        }
    }

    public void paint(Graphics g){
        g = (Graphics2D)g;
        g.drawImage(img,RL.p.layer1,0,null);
        g.drawImage(img,RL.p.layer2, 0, null);
        double player_v = ( 200/(RL.p.MAX_SPEED*2)) * RL.p.speed * 2;
        Font font = new Font("Times New Roman",Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Speed: " + player_v + " km/h", 50, 30);
        RL.TmpRoadChanges();
        DrawPriorityLine(g);
    }


    private void TestCollisionsWithEnemies(){
        if(RL.TestCollisionsWithEnemies()){
            JOptionPane.showMessageDialog(null, "YOU LOST");
            System.exit(1);
        }
    }

    public void DrawPriorityLine(Graphics g){
        g = (Graphics2D)g;
        RL.SortEnemies();
        boolean player_drawn = false;
        for (Enemy enemy:RL.enemyList){
            if(enemy.priority > RL.p.priority && !player_drawn){
                g.drawImage(RL.p.image, RL.p.x, RL.p.y, null);
                player_drawn = true;
            }
            g.drawImage(enemy.image, enemy.x, enemy.y, null);
        }
        if(!player_drawn){
            g.drawImage(RL.p.image, RL.p.x, RL.p.y, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RL.RoadLive();
        repaint();
        TestCollisionsWithEnemies();
    }

}