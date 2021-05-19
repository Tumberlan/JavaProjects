package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Car {


    public Player(int h, int w, Image img_c, Image img_u, Image img_d, int max_top,
                  int max_bot, int x_c, int y_c, int max_speed, int min_speed) {
        super(h, w, img_c, img_u, img_d, max_top, max_bot, x_c, y_c, max_speed, min_speed);
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, width, height);
    }


    int dy = 0;

    int layer1 = 0;
    int layer2 = 1200;



    public void move(){
        way_length += speed;
        speed += acceleration;
        if(speed <= MIN_SPEED){
            speed = MIN_SPEED;
        }
        if(speed >= MAX_SPEED){
            speed = MAX_SPEED;
        }
        if(speed > MIN_SPEED) {
            y += dy;
            if (y <= MAX_TOP) {
                y = MAX_TOP;
            }
            if (y >= MAX_BOTTOM) {
                y = MAX_BOTTOM;
            }
        }
        if(layer2-speed <= 0){
            layer1 = 0;
            layer2 = 1200;
        }
        layer1 -= speed;
        layer2 -= speed;
    }

    public void keyPressed(KeyEvent event){
        int key_code = event.getKeyCode();
        if(key_code == KeyEvent.VK_RIGHT){
            acceleration = 1;
        }
        if(key_code == KeyEvent.VK_LEFT){
            acceleration = -1;
        }
        if(key_code == KeyEvent.VK_UP){
            if(y > MAX_TOP) {
                image = image_up;
            }
            dy = -5;
        }
        if(key_code == KeyEvent.VK_DOWN){
            if(y < MAX_BOTTOM) {
                image = image_down;
            }
            dy = +5;
        }
    }
    public void keyReleased(KeyEvent event){
        int key_code = event.getKeyCode();
        if (key_code == KeyEvent.VK_RIGHT || key_code == KeyEvent.VK_LEFT){
            acceleration = 0;
        }
        if(key_code == KeyEvent.VK_UP || key_code == KeyEvent.VK_DOWN){
            image = image_central;
            dy = 0;
        }
    }
}
