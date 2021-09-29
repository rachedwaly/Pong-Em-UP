import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

public class Projectile extends Entity {
    public int damage = 10;
    public boolean fired = false;

    public Projectile(int w, int h, int dmg, int[] speed){
        this.x = 500;
        this.y = 1000;
        width = 5;
        height = 20;
        color = Color.RED;
        this.speed = speed;

    }

    @Override
    public void update() {
        move();
    }

    public void fire(Entity source){
        x = source.getX() + source.getWidth()/2 - width/2; // centrer le projectile sur la source
        y = source.getY() + source.lookDirection[1]*height ;
        speed[0] = Math.abs(speed[0]) * source.lookDirection[0];
        speed[1] = Math.abs(speed[1]) * source.lookDirection[1];
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
        return new Rectangle(x,y,width,height);
    }

    @Override
    public void drawEntity(Graphics g) {
        g.setColor(this.color);
        g.fillRect(x,y,width,height);
    }


}
