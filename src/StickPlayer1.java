import java.awt.*;
import java.awt.event.*;


import static java.lang.Math.min;
//this class will be usefull if we ever work in the versus version of this game
public class StickPlayer1 extends Stick {


    public StickPlayer1(int x, int y){
        super(x,y);
    }



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            speed[0] =-5;
            dx = speed[0];

        }

        if (key == KeyEvent.VK_RIGHT) {
            speed[0] =5;
            dx = speed[0];

        }

        if (key == KeyEvent.VK_UP) {
            speed[1] =5;
            dy = -speed[1];

        }

        if (key == KeyEvent.VK_DOWN) {
            speed[1] =-5;
            dy = -speed[1];

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();


        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            speed[0] =0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            speed[0] =0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
            speed[1] =0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            speed[1] =0;
        }
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(getX()+1,getY(),getWidth(),3);
    }


}
