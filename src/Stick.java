import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Stick extends Shooter{

    private Color healthColor;
    private Color damageColor;
    static int BASE_WIDTH = 50;
    public float offsetX;
    protected float dx,dy;
    private int score;

    public Stick(int x, int y){
        super(x,y);

        name = "Stick";
        canShoot = true;
        maxHealth = 5;
        health = maxHealth;
        healthColor = Color.GREEN;
        damageColor = Color.RED;

        offsetX = 0;
        this.width = BASE_WIDTH;
        this.height = 10;

        lookDirection = new int[]{0,-1};
        for(int i = 0; i < projectiles.length; i++)
            projectiles[i] = new Projectile(5,20,10,new float[]{5f,5f});
    }

    @Override
    public void update() {
        if(innerTimer > 80){
            color = Color.BLACK;
        }

        innerTimer += Model.DELAY;
        move();
    }

    @Override
    public void whenCollided(Entity entity) {
        color = Color.RED;
        switch(entity.getEntityTypeName()){
            case "projectile":
                Projectile p = (Projectile) entity;
                health -= p.damage;
                if(health <=0){
                    width = 0;
                    //play destruction animation;
                    System.out.println("Lost !");
                }else{
                    offsetX += (int)(p.damage/(float)maxHealth * BASE_WIDTH/4);
                    width = BASE_WIDTH/2 + (int)((health/(float)maxHealth) * BASE_WIDTH/2);
                }

                //100 hp - 20 dgt : 80
                //on bouge de 10 hp vers la droite, on perd 20 hp

                break;
            case "Enemy":

            default :
                break;
        }
        innerTimer = 0;
    }

    @Override
    public String getEntityTypeName() {
        return "stick";
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
                System.out.println("isFiring");
                fire();
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

    public int getHealth(){
        return health;
    }

    public int getScore(){
        return score;
    }
    @Override
    public void drawEntity(Graphics g){


        g.setColor(this.color);
        g.fillRect((int)x,(int)y,width,height);
        g.setColor(healthColor);
        g.fillRect((int)(x - offsetX),(int)y + height + 5,(int)(BASE_WIDTH * health/(float)maxHealth),5);
        g.setColor(damageColor);
        g.fillRect(     (int)(x - offsetX) + (int)(BASE_WIDTH * health/(float)maxHealth),
                        (int)y + height + 5,(int)(BASE_WIDTH * (maxHealth - health)/(float)maxHealth),5);
    }

    /*@Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        PhysicalBoundarie c1=new PhysicalBoundarie((int)x,(int)y,width,3); //top side
        PhysicalBoundarie c2=new PhysicalBoundarie((int)x,(int)y+3,2,height-6); //left side
        PhysicalBoundarie c3=new PhysicalBoundarie((int)x+width-2,(int)y+3,2,height-6); //right side
        PhysicalBoundarie c4=new PhysicalBoundarie((int)x,(int)y+height-3,width,3); //right side
        ArrayList <PhysicalBoundarie> list=new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        return list;
    }*/
}
