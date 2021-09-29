import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

public class Projectile extends Entity {
    public int damage = 10;
    public boolean active = false;

    public Projectile(int w, int h, int dmg, int[] speed){
        this.x = 500;
        this.y = 1000;
        width = 5;
        height = 20;
        color = Color.RED;
        this.speed = speed;

        lookDirection = new int[]{0,0};
    }

    @Override
    public void update() {
        move();
        if(y < 0 || 600 < y){
            active = false; //placeholder, should deactivate when collision
            lookDirection[0] = 0;
            lookDirection[1] = 0;
        }
    }

    public void fire(Entity source){
        x = source.getX() + source.getWidth()/2 - width/2; // centrer le projectile sur la source
        y = source.getY() + source.lookDirection[1]*height ;
        speed[0] = Math.abs(speed[0]) * source.lookDirection[0];
        speed[1] = Math.abs(speed[1]) * source.lookDirection[1];
        active = true;
        lookDirection = source.lookDirection.clone();
    }

    public void move(){
        x = Math.max(-100,Math.min(500, x + speed[0])); //pas trop aller out of bounds
        y = Math.max(-100,Math.min(1000,y + speed[1]));
    }

    public void solveCollisions(){
        Entity entityHit;
        //TODO
    }
    //TODO : add les physicial boundaries
    public Rectangle getBounds(){
        if(active){
            if(lookDirection[1] == 1)
                return new Rectangle(x,y + height*3/4,width,height/4);
            if(lookDirection[1] == -1)
                return new Rectangle(x,y,width,height/4);
        }else
            return new Rectangle(0,0,0,0);
        return null;
    }

    @Override
    public void drawEntity(Graphics g) {
        if(active){
            g.setColor(this.color);
            g.fillRect(x,y,width,height);
            g.setColor(Color.BLACK);
            if(lookDirection[1] == 1)
                g.fillRect(x,y + height*3/4,width,height/4);
            if(lookDirection[1] == -1)
                g.fillRect(x,y,width,height/4);
        }

    }

    @Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        return null;
    }


}
