import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

public class Projectile extends Entity {
    public int damage = 10;
    public boolean fired = false;

    public Projectile(int x, int y){
        //super(x,y);
        this.x = 500;
        this.y = 1000;
        width = 5;
        height = 20;
        color = Color.RED;
        speed = new int[]{2,2};

    }

    public void fire(Entity source){
        x = source.x + width/2;
        y = source.y;
        speed[0] = Math.abs(speed[0]) * source.lookDirection[0];
        speed[1] = Math.abs(speed[1]) * source.lookDirection[1];
    }

    public void move(){
        x = Math.max(-10,Math.min(500, x + speed[0])); //pas trop aller out of bounds
        y = Math.max(-10,Math.min(1000,y + speed[1]));
    }

    public void solveCollisions(){
        Entity entityHit;
        //TODO
    }

    @Override
    public void drawEntity(Graphics g) {
        g.setColor(this.color);
        g.fillRect(x,y,width,height);
    }


}
