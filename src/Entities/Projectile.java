package Entities;
import Game.Model;
import shape.CircleShape;
import shape.CustomShape;
import shape.RectangleShape;

import java.awt.*;

public class Projectile extends Entity {
    public int damage = 1;
    public boolean active = false;
    public float scalarSpeed;
    public boolean friendly; //true if ally projectile, false if enemy;

    public Projectile(int w, int h, int dmg, float scalarSpeed,CustomShape shape, Model model){
        super(model);
        name = "Projectile";
        this.x = 500;
        this.y = 1000;
        width = w;
        height = h;
        color = Color.RED;
        this.scalarSpeed = scalarSpeed;
        this.shape = shape;
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
        x = 500;
        y = 1000;
        active = false;
    }

    @Override
    public String getEntityTypeName() {
        if(friendly)
            return "stickprojectile";
        else
            return "enemyprojectile";
    }

    public void fire(Shooter source, float[] fireDirection){
        fireDirection = CustomShape.normalize(fireDirection);
        friendly = source.getEntityTypeName().equals("stick");
        x = source.getX() + source.getWidth()/2f - width/2f + fireDirection[0] * (source.getWidth()/2f + 10); // centrer le projectile sur la source
        y = source.getY() + source.getHeight()/2f + fireDirection[1] * (source.getHeight()/2f + height); //grab look direction

        speed[0] = scalarSpeed * fireDirection[0];
        speed[1] = scalarSpeed * fireDirection[1];
        active = true;

    }

    public void move(){
        x = Math.max(-100,Math.min(500, x + speed[0])); //pas trop aller out of bounds
        y = Math.max(-100,Math.min(1000,y + speed[1]));
        shape.update(this);
    }

    @Override
    public CustomShape getShape() {
        if(active)
            return shape;
        else
            return new RectangleShape(0,0,0,0);
    }

    @Override
    public void drawEntity(Graphics g) {
        if(active){
            g.setColor(Color.RED);
            if(shape instanceof RectangleShape)
                g.fillRect((int)x,(int)y,width,height);
            if(shape instanceof CircleShape)
                g.fillOval((int)x,(int)y,width,height);
            g.setColor(this.color);
        }


    }

}
