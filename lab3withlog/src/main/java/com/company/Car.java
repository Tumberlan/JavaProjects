package com.company;

import java.awt.*;

public class Car {
    int height;
    int width;
    Image image;
    Image image_central;
    Image image_up;
    Image image_down;
    int MAX_TOP;
    int MAX_BOTTOM;
    int MAX_SPEED;
    int MIN_SPEED;

    int x;
    int y;
    int speed;
    int acceleration;
    int way_length;


    public Car(int h, int w, Image img_c, Image img_u, Image img_d, int max_top, int max_bot,
               int x_c, int y_c,int max_speed, int min_speed){
        height = h;
        width = w;
        image_central = img_c;
        image_up = img_u;
        image_down = img_d;
        image = image_central;
        MAX_TOP = max_top;
        MAX_BOTTOM = max_bot;
        speed = 0;
        acceleration = 0;
        way_length = 0;
        x = x_c;
        y = y_c;
        MAX_SPEED = max_speed;
        MIN_SPEED = min_speed;
    }
}
