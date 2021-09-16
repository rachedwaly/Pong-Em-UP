import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.min;

public class StickPlayer2 extends Stick {


    public StickPlayer2(int x, int y){

        super(x,y);


    }



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Z) {

            speed=5;
            dx = -speed;
        }

        if (key == KeyEvent.VK_S) {

            speed=-5;
            dx = -speed;
        }
    }


    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        speed=0;

        if (key == KeyEvent.VK_Z) {
            dx = 0;
        }

        if (key == KeyEvent.VK_S) {
            dx = 0;
        }

    }
    public Rectangle getBounds(){
        return new Rectangle(getX()-1,getY(),1,getHeight());
    }
}