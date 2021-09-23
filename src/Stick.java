import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Stick extends Entity{

    protected int dx,dy;


    public Stick(int x, int y){
        super(x,y);
        this.width=40;
        this.height=10;
        orientation=true;

    }
    public void move(){

        if ((x>=10) && (x<WIDTH/2)) {
            this.x = max(this.x+ dx,10);
        }
        else{
            this.x=min(this.x+ dx,WIDTH-10-width);
        }

        if ((y<=HEIGHT-20) && (y>HEIGHT*3/4)){
            this.y=min(this.y+dy,HEIGHT-30);
        }
        else{
            this.y=max(this.y+dy,HEIGHT*3/4);
        }


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

    @Override
    public void drawEntity(Graphics g){
        g.fillRect(getX(),getY(),getWidth(),10);
    }







}
