import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Stick extends Entity{

    protected int dx,dy;
    private int projectileIndex;
    private boolean canShoot = true;
    public Projectile[] projectiles = new Projectile[PROJECTILEBUFFER]; //Ten buffered projectiles, 11 is empty


    public Stick(int x, int y){
        super(x,y);
        this.width=40;
        this.height=10;
        orientation=true;
        lookDirection = new int[]{0,-1};
        for(int i = 0; i < projectiles.length; i++)
            projectiles[i] = new Projectile(5,20,10,new float[]{5f,5f});
    }

    @Override
    public void update() {
        move();
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
            speed[1] =-2;
            dy = speed[1];

        }

        if (key == KeyEvent.VK_DOWN) {
            speed[1] =2;
            dy = speed[1];

        }
        if (key == KeyEvent.VK_SPACE){
            if(canShoot){
                projectiles[projectileIndex].fire(this); // shoot vertically
                canShoot = false;
            }
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
        if (key == KeyEvent.VK_SPACE){
            canShoot = true;
            projectileIndex = (projectileIndex + 1)%PROJECTILEBUFFER;
        }
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,width,10);
    }

    @Override
    public void drawEntity(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect((int)x,(int)y,width,10);
    }

    @Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        PhysicalBoundarie c1=new PhysicalBoundarie((int)x,(int)y,width,3,true); //top side
        PhysicalBoundarie c2=new PhysicalBoundarie((int)x,(int)y+3,2,height-6,false); //left side
        PhysicalBoundarie c3=new PhysicalBoundarie((int)x+width-2,(int)y+3,2,height-6,false); //right side
        PhysicalBoundarie c4=new PhysicalBoundarie((int)x,(int)y+height-3,width,3,true); //right side
        ArrayList <PhysicalBoundarie> list=new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        return list;
    }
}
