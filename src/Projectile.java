import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.Math.abs;

public class Projectile extends Entity {
    public int damage = 10;
    public boolean active = false;

    public Projectile(int w, int h, int dmg, float[] speed){
        name = "Projectile";
        this.x = 500;
        this.y = 1000;
        width = 5;
        height = 20;
        color = Color.RED;
        this.speed = speed;

        lookDirection = new int[]{0,0};
    }

    @Override
    public void update(ArrayList<Entity> eList) {
        solveCollisions(eList);
        move();
        if(y < 0 || 600 < y){
            active = false; //placeholder, should deactivate when collision
            lookDirection[0] = 0;
            lookDirection[1] = 0;
        }
    }

    @Override
    public void whenCollided(Entity entity) {

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
                return new Rectangle((int)x,(int)y + height - 1,width,1);
            if(lookDirection[1] == -1)
                return new Rectangle((int)x,(int)y,width,1);
        }else
            return new Rectangle((int)x,(int)y,100,100);
        return null;
    }

    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries(){
        PhysicalBoundarie square = new PhysicalBoundarie((int)x,(int)y,width,1,false);

        ArrayList <PhysicalBoundarie> list=new ArrayList<>();
        list.add(square);

        return list;
    }

    public void solveCollisions(ArrayList<Entity> list) {

        if(active){
            //System.out.println("flood gaming");
            for (Entity entity : list) {
                if (this.getBounds().intersects(entity.getBounds())) {//checking with which object the ball collide
                    System.out.println("hit entity : " + entity.name);
                    x = -100;
                    y = -100;
                    active = false;
                    //deal damage
                }
                break;
            }
        }
    }


    @Override
    public void drawEntity(Graphics g) {
        if(active){
            g.setColor(this.color);
            g.fillRect((int)x,(int)y,width,height);
            g.setColor(Color.BLACK);
            if(lookDirection[1] == 1)
                g.fillRect((int)x,(int)y + height*2/3,width,height/3);
            if(lookDirection[1] == -1)
                g.fillRect((int)x,(int)y,width,height/3);
        }

    }


}
