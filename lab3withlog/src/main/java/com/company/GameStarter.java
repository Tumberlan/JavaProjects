package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStarter extends JPanel implements Runnable{
    Image image = new ImageIcon("src/res/Greetings.png").getImage();
    JButton button = new JButton("START");

    public GameStarter(){
        button_init();
        setFocusable(true);
        this.add(button);
        this.revalidate();
        this.repaint();

    }

    public void button_init(){
        button.setPreferredSize(new Dimension(300, 100));
        button.setIcon(new ImageIcon("src/res/race_wheel.png"));
        button.setRolloverIcon(new ImageIcon("src/res/race_wheel_rollover.png"));
        button.setPressedIcon (new ImageIcon("src/res/race_wheel_pressed.png"));
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


/*
    public void buttonShow(){
        Container container = getContentPane();
        JButton button = new JButton("START");
        container.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 10));
        button.setIcon(new ImageIcon("src/res/race_wheel.png"));
        button.setRolloverIcon(new ImageIcon("src/res/race_wheel_rollover.png"));
        button.setPressedIcon (new ImageIcon("src/res/race_wheel_pressed.png"));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        container.add(button);
        setVisible(true);
    }*/
    public void paint(Graphics g){

        g = (Graphics2D)g;
        g.drawImage(image,0,0,null);


    }

    @Override
    public void run(){

    }
}
