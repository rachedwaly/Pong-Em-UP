import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

public class Projectile extends Entity {
    public int damage = 1;
    public boolean active = false;
    public float[] absSpeed;

    public Projectile(int w, int h, int dmg, float[] speed){
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
        x = -100;
        y = -100;
        active = false;
    }

    @Override
    public String getEntityTypeName() {
        return "projectile";
    }

    public void fire(Shooter source){
        x = source.getX() + source.getWidth()/2 - width/2; // centrer le projectile sur la source
        y = source.getY() + source.lookDirection[1]*height ; //grab look direction dynamically, some ships might change direction
        speed[0] = absSpeed[0] * source.lookDirection[0];
        speed[1] = absSpeed[1] * source.lookDirection[1];
        active = true;
    }

    public void move(){
        x = Math.max(-100,Math.min(500, x + speed[0])); //pas trop aller out of bounds
        y = Math.max(-100,Math.min(1000,y + speed[1]));
    }

    //TODO : add les physicial boundaries
    public Rectangle getBounds(){
        if(active){
            if(speed[1] > 0)
                return new Rectangle((int)x,(int)y,width,height);
            else
                return new Rectangle((int)x,(int)y,width,height);
        }else
            return new Rectangle(0,0,0,0);
    }

    /*public ArrayList<PhysicalBoundarie> getPhysicalBoundaries(){
        PhysicalBoundarie square = new PhysicalBoundarie((int)x,(int)y,width,1,true);

        ArrayList <PhysicalBoundarie> list=new ArrayList<>();
        list.add(square);

        return list;
    }*/

    @Override
    public void drawEntity(Graphics g) {
        if(active){
            g.setColor(this.color);
            g.fillRect((int)x,(int)y,width,height);
            g.setColor(Color.BLACK);
            if(speed[1] > 0)
                g.fillRect((int)x,(int)y + height*2/3,width,height/3);
            else
                g.fillRect((int)x,(int)y,width,height/3);
        }

    }


}
