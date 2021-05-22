package com.company;

import javax.swing.*;
import java.awt.*;

public class Enemy extends Car{

    public Enemy(Car car, int x_coord, int y_coord, int speed_value, Road r) {
        super(car.name,car.height, car.width, car.image_central, car.image_up, car.image_down,
                car.MAX_TOP, car.MAX_BOTTOM, car.x, car.y, car.MAX_SPEED, car.MIN_SPEED, car.MAX_X,
                car.MIN_X, car.random_number_top, car.random_number);
        x = x_coord;
        y = y_coord;
        speed = speed_value;
        road = r;
    }

    Road road;

    public Rectangle getRect(){
        return new Rectangle(x, y, width, height);
    }


    public void move(){
        x = x - road.player.speed + speed;
    }
}
