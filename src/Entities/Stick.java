package Entities;
import Game.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Stick extends Shooter{

    private Color healthColor;
    private Color damageColor;
    static int BASE_WIDTH = 50;
    public float offsetX;
    protected float dx,dy;
    private int score;



    private int spawnLeft=3;

    public Stick(int x, int y,Model model){
        super(x,y,model);

        name = "Stick";
        canShoot = true;
        maxHealth = 5;
        health = maxHealth;
        healthColor = Color.GREEN;
        damageColor = Color.RED;

        offsetX = 0;
        this.width = BASE_WIDTH;
        this.height = 15;

        lookDirection = new int[]{0,-1};
        for(int i = 0; i < projectiles.length; i++)
            projectiles[i] = new Projectile(5,20,10,new float[]{5f,5f},model);
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

        switch (entity.getEntityTypeName()) {
            case "projectile":
                color = Color.RED;
                if (!Model.DEBUGMODE) {
                    Projectile p = (Projectile) entity;
                    health -= p.damage;
                    offsetX += (int) (p.damage / (float) maxHealth * BASE_WIDTH / 4);
                    width = BASE_WIDTH / 2 + (int) ((health / (float) maxHealth) * BASE_WIDTH / 2);

                }

                break;
            case "enemy":
                health = 0;
                break;
            case "ball":
                //TODO : play bong effect
                break;
            default :
                break;
        }
        if(health <=0){
            if (spawnLeft==0){
            //play destruction animation;
            model.stopTheGame();
            System.out.println("Lost !");
            }
            else {

                spawnLeft-=1;
            }
        }
        innerTimer = 0;
    }

    @Override
    public void startDestructionSequence(Graphics g) {
        if (spawnLeft > 0){
            if (animationIndex <= maxAnimationIndex){
                g.drawImage(model.getPhoto(Integer.toString(animationIndex)+"death"),
                        (int)x-BASE_WIDTH,
                        (int)y-height*4,
                        BASE_WIDTH*4,
                        height*8,model.getView());
                animationIndex++;
            }
            else{
                resetStick();
            }
        }else{
            //dessiner une image de destruction intermédiaire
            g.drawImage(model.getPhoto(Integer.toString(5)+"death"),(int)x-BASE_WIDTH,
                    (int)y-height*4,
                    BASE_WIDTH*4,
                    height*8,model.getView());
            model.stopTheGame();
        }
    }

    @Override
    public String getEntityTypeName() {
        return "stick";
    }

    public void move(){

        if ((x>=10) && (x<WIDTH/2f)) {
            this.x = max(this.x+ dx,10);
        }
        else{
            this.x=min(this.x+ dx,WIDTH-10-width);
        }

        if ((y<=HEIGHT-20) && (y>HEIGHT*3f/4)){
            this.y=min(this.y+dy,HEIGHT-30);
        }
        else{
            this.y=max(this.y+dy,HEIGHT*3f/4);
        }
        shape.update(this);

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
        if (health>0){
        g.setColor(this.color);
        g.fillRect((int)x,(int)y,width,height);
        g.setColor(healthColor);
        g.fillRect((int)(x - offsetX),(int)y + height + 5,(int)(BASE_WIDTH * health/(float)maxHealth),5);
        g.setColor(damageColor);
        g.fillRect(     (int)(x - offsetX) + (int)(BASE_WIDTH * health/(float)maxHealth),
                        (int)y + height + 5,
                (int)(BASE_WIDTH * (maxHealth - health)/(float)maxHealth),5);
        }
        else {
            startDestructionSequence(g);

        }

    }

    private void resetStick() {
        offsetX=0;
        width=BASE_WIDTH;
        health=maxHealth;
        animationIndex=1;
    }

    public int getSpawnLeft() {
        return spawnLeft;
    }

    public void setSpawnLeft(int spawnLeft) {
        this.spawnLeft = spawnLeft;
    }




}
