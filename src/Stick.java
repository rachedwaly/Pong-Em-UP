import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
        return new Rectangle(getX(),getY(),getWidth(),10);
    }

    @Override
    public void drawEntity(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(getX(),getY(),getWidth(),10);
    }

    @Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        PhysicalBoundarie c1=new PhysicalBoundarie(getX()+2,getY(),getWidth()-4,getHeight(),true);
        PhysicalBoundarie c2=new PhysicalBoundarie(getX(),getY(),1,getHeight(),false); //left side
        PhysicalBoundarie c3=new PhysicalBoundarie(getWidth()-1,getY(),1,getHeight(),false); //right side
        ArrayList <PhysicalBoundarie> list=new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        return list;
    }
}
