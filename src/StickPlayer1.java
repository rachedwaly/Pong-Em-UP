import java.awt.*;
import java.awt.event.*;
import java.security.Key;


import static java.lang.Math.min;

public class StickPlayer1 extends Stick {


    public StickPlayer1(int x, int y){
        super(x,y);
    }



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            speed=5;
            dy = -speed;

        }

        if (key == KeyEvent.VK_DOWN) {
            speed=-5;
            dy = -speed;

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        speed=0;
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(getX()+getWidth(),getY(),1,getHeight());
    }
}
