package Entities;
import Game.Model;
import shape.CustomRectangle;
import shape.RectangleShape;

import java.awt.*;

public class Projectile extends Entity {
    public int damage = 1;
    public boolean active = false;
    public float[] absSpeed;

    public Projectile(int w, int h, int dmg, float[] speed, Model model){
        super(model);
        name = "Projectile";
        this.x = 500;
        this.y = 1000;
        width = 5;
        height = 20;
        color = Color.RED;
        absSpeed = speed;

    }

    @Override
    public void update() {
        move();
        if(y < 0 || 600 < y){
            active = false;
        }
    }

    @Override
    public void whenCollided(Entity entity) {
        active = false;
    }

    @Override
    public String getEntityTypeName() {
        return "projectile";
    }

    public void fire(Shooter source){
        x = source.getX() + source.getWidth()/2f - width/2f; // centrer le projectile sur la source
        y = source.getY() + source.lookDirection[1]*(source.height+10) ; //grab look direction
        //y = source.getY() + source.lookDirection[1]*height;
        // dynamically, some ships might change direction
        speed[0] = absSpeed[0] * source.lookDirection[0];
        speed[1] = absSpeed[1] * source.lookDirection[1];
        active = true;

        if(speed[1] > 0)
            shape = new RectangleShape((int)x,(int)y + 2 * height/3,width,height/3);
        else
            shape = new RectangleShape((int)x,(int)y,width,height/3);
    }

    public void move(){
        x = Math.max(-100,Math.min(500, x + speed[0])); //pas trop aller out of bounds
        y = Math.max(-100,Math.min(1000,y + speed[1]));
    }


    @Override
    public void drawEntity(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x + width/4,(int)y,width/2,height);
        g.setColor(this.color);
        if(speed[1] > 0)
            g.fillRect((int)x,(int)y + height*2/3,width,height/3);
        else
            g.fillRect((int)x,(int)y,width,height/3);
    }

}
