package Entities;
import AltLib.ImageLoader;
import Entities.Bonus.Bonus;
import Game.*;
import shape.RectangleShape;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import static java.lang.Math.*;

/***
 * Stick object to bounce the ball with
 * Credit : Rached
 */
public class Stick extends Shooter{
    public static final int RESPAWN_TIME = -2000;
    private ArrayList<Bonus> bonuses=new ArrayList<>();
    private Color healthColor;
    private Color damageColor;
    protected BufferedImage texture;



    //static int BASE_WIDTH = 50; je vais changer ça en initialwidth sinon la barre verte va pas
    // changer avec le bonus length qui dépend
    private int initialWidth;
    public float offsetX;
    protected float dx,dy;


    private int score;


    private boolean shieldOn=false;

    private boolean respawning = false;
    private int lives =3;

    public Stick(int x, int y,Model model,int initialWidth){
        super(x,y,model);
        this.initialWidth=initialWidth;
        name = "Stick";
        canShoot = true;
        maxHealth = 5;
        health = maxHealth;
        healthColor = Color.GREEN;
        damageColor = Color.RED;
        offsetX = 0;
        this.width = initialWidth;
        this.height = 10;

        lookDirection = new float[]{0,-1};
        for(int i = 0; i < projectiles.length; i++)
            projectiles[i] = new Projectile(5,10,5,new RectangleShape(0,0,5,10), model);
    }

    @Override
    public void update() {
        innerTimer += Model.DELAY;

        if(!respawning){
            if(innerTimer > 80){
                color = Color.BLACK;
            }

            for (int i=0;i<bonuses.size();i++){
                Bonus bonus=bonuses.get(i);
                bonus.decrementDelay();
            }
            move();
        }else{
            if(innerTimer >= RESPAWN_TIME){
                //Stick respawning
                respawning = false;
                x = 100;
                y = 500;
                shape = new RectangleShape((int)x,(int)y,width,height);
            }


        }

    }

    @Override
    public void whenCollided(Entity entity) {

        switch (entity.getEntityTypeName()) {
            case "enemyprojectile":
                if (!shieldOn && innerTimer >= 0) { //stick is invulnerable for some time after respawning
                    color = Color.RED;
                    Projectile p = (Projectile) entity;
                    health -= p.damage;
                    offsetX += (int) (p.damage / (float) maxHealth * initialWidth / 4);
                    width = initialWidth / 2 + (int) ((health / (float) maxHealth) * initialWidth / 2);
                }

                break;
            case "enemy":
                if (!shieldOn && innerTimer >= 0)
                    health = 0;
                break;
            case "ball":
                //TODO : play bong effect
                break;
            default :
                break;
        }

        innerTimer = min(0,innerTimer);
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
            if(canShoot && !respawning){
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
        if((innerTimer > 0 || innerTimer % 300 > -150) && !respawning){
            if (health>0){
                texture=ImageLoader.stickImage[abs(Model.stickPhoto%ImageLoader.stickImage.length)];
                g.drawImage(texture,(int)x, (int)y,width,height,model.getView());
                g.setColor(healthColor);
                g.fillRect((int)(x - offsetX),(int)y + height + 5,(int)(initialWidth * health/(float)maxHealth),5);
                g.setColor(damageColor);
                g.fillRect((int) (x - offsetX) + (int) (initialWidth * health / (float) maxHealth),
                        (int) y + height + 5,
                        (int) (initialWidth * (maxHealth - health) / (float) maxHealth), 5);
            }
            else {
                startDestructionSequence(g);
            }
        }
    }

    @Override
    public void startDestructionSequence(Graphics g) {
        if (animationIndex < maxAnimationIndex){
            g.drawImage(ImageLoader.explosionAnimation[animationIndex],
                    (int)x-initialWidth,
                    (int)y-height*4,
                    initialWidth*4,
                    height*8,model.getView());
            animationIndex++;
        }
        else
            destroy();

    }

    /***
     * Stops the stick from being drawn for 5 seconds, after which it respawns
     */
    private void destroy() {
        if (lives<=0){
            model.stopTheGame();
        }else{
            lives -= 1;
            shape = new RectangleShape(0,0,0,0);
            innerTimer = RESPAWN_TIME - 1000;
            respawning = true;
            offsetX=0;
            width=initialWidth;
            health=maxHealth;
            animationIndex=1;
        }

    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void applyBonus(Bonus bonus){
        bonuses.add(bonus);
        switch(bonus.name){
        case "shield":
        {
            shieldOn=true;
            break;
        }
        case "lengthBoost":
        {
            //TODO lengthBoost
            break;
        }
    }
    }


    public boolean isShieldOn() {
        return shieldOn;
    }

    public void setShieldOn(boolean shieldOn) {
        this.shieldOn = shieldOn;
    }

    public int getInitialWidth() {
        return initialWidth;
    }

    public void setInitialWidth(int initialWidth) {
        this.initialWidth = initialWidth;
    }

    @Override
    public void setHealth(int health){
        this.health=health;
        width = initialWidth / 2 + (int) ((health / (float) maxHealth) * initialWidth / 2);
    }

    public void setScore(int score) {
        this.score = score;
    }


}
