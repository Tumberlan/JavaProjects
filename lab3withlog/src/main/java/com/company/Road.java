package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Road extends JPanel implements ActionListener{
    Image img = new ImageIcon("src/res/game_road.png").getImage();
    RoadLogic RL;
    Timer mainTimer = new Timer(40, this);
    int playerType;

    public Road(int pT, RoadLogic rl){
        RL = rl;
        playerType = pT;
        mainTimer.start();
        if(playerType == 1) {
            addKeyListener(new MyKeyAdapter());
        }else{
            addKeyListener(new MyKeyAdapter2());
        }
        setFocusable(true);
    }

    private class MyKeyAdapter extends KeyAdapter{
            public void keyPressed (KeyEvent event){
                RL.player.keyPressed(event);
            }
            public void keyReleased (KeyEvent event){
                RL.player.keyReleased(event);
            }

    }
    private class MyKeyAdapter2 extends KeyAdapter{
        public void keyTyped(KeyEvent event){
            RL.player2.keyTyped(event);
        }
    }

    public void paint(Graphics g){
        g = (Graphics2D)g;
        g.drawImage(img, RL.player.layer1, 0, null);
        g.drawImage(img, RL.player.layer2, 0, null);
        if(playerType == 1) {
            double player_v = (200 / (RL.player.MAX_SPEED * 2)) * RL.player.speed * 2;
            Font font = new Font("Times New Roman", Font.ITALIC, 20);
            g.setFont(font);
            g.drawString("Speed: " + player_v + " km/h", 50, 30);
        }else {
            Font font = new Font("Times New Roman", Font.ITALIC, 20);
            g.setFont(font);
            g.drawString("Car Type: " + RL.player2.carName + "  Line: " +
                    RL.player2.lineNumber, 350, 30);
        }
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
        for (Enemy enemy : RL.enemyList) {
            if (enemy.priority > RL.player.priority && !player_drawn) {
                g.drawImage(RL.player.image, RL.player.x, RL.player.y, null);
                player_drawn = true;
            }
            g.drawImage(enemy.image, enemy.x, enemy.y, null);
        }
        if (!player_drawn) {
            g.drawImage(RL.player.image, RL.player.x, RL.player.y, null);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RL.RoadLive();
        repaint();
        TestCollisionsWithEnemies();
    }

}