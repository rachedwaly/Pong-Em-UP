import java.awt.*;
import java.awt.event.*;


import static java.lang.Math.min;

public class StickPlayer1 extends Stick {


    public StickPlayer1(int x, int y){
        super(x,y);
    }



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            speed=5;
            dx = -speed;

        }

        if (key == KeyEvent.VK_RIGHT) {
            speed=-5;
            dx = -speed;

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        speed=0;
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(getX(),getY(),getWidth(),1);
    }
}
