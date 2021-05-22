package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;

public class FirstLayout {
    public static JFrame frame;
    public static GameStarter jPanel = new GameStarter();
    public static int WINDOW_WIDTH = 1200;
    public static int WINDOW_HEIGHT = 655;
    Image image = new ImageIcon("src/res/Greetings.png").getImage();

    public void StartLayout(){
        frame = GetFrame();
        AbstractAction myAction = new MyAction();
        frame.add(jPanel);
        JButton jButton = new JButton(myAction);
        jButton.setText("BANDIT");
        jPanel.add(jButton);

/*
        KeyStroke keyStroke = KeyStroke.getKeyStroke("ctrl B");
        InputMap inputMap = jPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(keyStroke,"changeColor");
        ActionMap actionMap = jPanel.getActionMap();
        actionMap.put("changeColor", myAction);
        */


    }


    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(image,0,0,null);
    }

    static class MyAction extends AbstractAction{
        MyAction(){
            putValue(AbstractAction.SHORT_DESCRIPTION, "AAAUUUU");
        }
        @Override
        public void actionPerformed(ActionEvent e){
            jPanel.setBackground(Color.BLACK);
        }
    }



    public static JFrame GetFrame(){
        JFrame f = new JFrame("Java F1");
        f.setVisible(true);
        f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return f;
    }

}
