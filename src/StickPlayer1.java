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
            speedx =-5;
            dx = speedx;

        }

        if (key == KeyEvent.VK_RIGHT) {
            speedx =5;
            dx = speedx;

        }

        if (key == KeyEvent.VK_UP) {
            speedy =5;
            dy = -speedy;

        }

        if (key == KeyEvent.VK_DOWN) {
            speedy =-5;
            dy = -speedy;

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();


        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            speedx =0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            speedx =0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
            speedy =0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            speedy =0;
        }
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(getX()+1,getY(),getWidth(),3);
    }


}
