import javax.swing.*;
import java.awt.*;

public class Enemy {
    int x;
    int y;
    int v;
    Image image = new ImageIcon("src/res/random_car1.png").getImage();
    Road road;

    public Rectangle getRect(){
        return new Rectangle(x, y, 251, 92);
    }

    public Enemy(int x_coord, int y_coord, int v_value, Road r){
        x = x_coord;
        y = y_coord;
        v = v_value;
        road = r;
    }

    public void move(){
        x = x - road.p.v + v;
    }
}
