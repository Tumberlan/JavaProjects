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
    Player p = new Player();

    Thread EnemiesFactory = new Thread(this);

    Thread Audio = new Thread(new AudioThread());

    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();



    public Road(){
        mainTimer.start();
        EnemiesFactory.start();
        Audio.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    private class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent event){
            p.keyPressed(event);
        }
        public void keyReleased(KeyEvent event){
            p.keyReleased(event);
        }
    }


    public void paint(Graphics g){
        g = (Graphics2D)g;
        g.drawImage(img,p.layer1,0,null);
        g.drawImage(img,p.layer2, 0, null);
        g.drawImage(p.img, p.x, p.y, null);
        double player_v = ( 200/(Player.MAX_V*2)) * p.v * 2;
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
            if(p.getRect().intersects(enemy.getRect())){
                JOptionPane.showMessageDialog(null, "YOU LOST");
                System.exit(1);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        p.move();
        MoveEnemies();
        repaint();
        TestCollisionsWithEnemies();
    }

    public void run(){
        while (true){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000)+2000);
                enemyList.add(new Enemy(1600, random.nextInt(350)+125,
                        random.nextInt(20)+20,this));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
