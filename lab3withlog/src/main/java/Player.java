import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static int MAX_V = 60;
    public static int MIN_V = 0;
    public static int MAX_TOP = 170;
    public static int MAX_BOTTOM = 535;
    public static int MAX_CRUISER_TOP = 125;
    public static int MAX_CRUISER_BOTTOM = 475;
    Image img_centre = new ImageIcon("src/res/ford_focus.png").getImage();
    Image img_d = new ImageIcon("src/res/ford_focus_down.png").getImage();
    Image img_u = new ImageIcon("src/res/ford_focus_up.png").getImage();

    Image img = img_centre;

    int v =  0;
    int dv = 0;
    int s = 0;

    //фокус
    int x = 20;
    int y = 205;
    public Rectangle getRect(){
        return new Rectangle(x, y, 236, 82);
    }
    //фура
    /*int x = 0;
    int y = 103;*/

    //субару
    /*int x = 30;
    int y = 208;*/

    //киа
    /*int x = 40;
    int y = 198;*/

    //крузак
    /*int x = 20;
    int y = 168;*/
    //черный крузак
    /*int x = 20;
    int y = 168;*/

    int dy = 0;

    int layer1 = 0;
    int layer2 = 1200;



    public void move(){
        s += v;
        v += dv;
        if(v <= MIN_V){
            v = MIN_V;
        }
        if(v >= MAX_V){
            v = MAX_V;
        }
        if(v > MIN_V) {
            y += dy;
            if (y <= MAX_TOP) {
                y = MAX_TOP;
            }
            if (y >= MAX_BOTTOM) {
                y = MAX_BOTTOM;
            }
        }
        if(layer2-v <= 0){
            layer1 = 0;
            layer2 = 1200;
        }
        layer1 -= v;
        layer2 -= v;
    }

    public void keyPressed(KeyEvent event){
        int key_code = event.getKeyCode();
        if(key_code == KeyEvent.VK_RIGHT){
            dv = 1;
        }
        if(key_code == KeyEvent.VK_LEFT){
            dv = -1;
        }
        if(key_code == KeyEvent.VK_UP){
            if(y > MAX_TOP) {
                img = img_u;
            }
            dy = -5;
        }
        if(key_code == KeyEvent.VK_DOWN){
            if(y < MAX_BOTTOM) {
                img = img_d;
            }
            dy = +5;
        }
    }
    public void keyReleased(KeyEvent event){
        int key_code = event.getKeyCode();
        if (key_code == KeyEvent.VK_RIGHT || key_code == KeyEvent.VK_LEFT){
            dv = 0;
        }
        if(key_code == KeyEvent.VK_UP || key_code == KeyEvent.VK_DOWN){
            img = img_centre;
            dy = 0;
        }
    }
}
