package Entities;
import Game.*;
import AltLib.*;
import shape.CircleShape;
import shape.RectangleShape;

import java.awt.*;

/***
 * Enemy units
 * Credit : Mostly Kevin
 */
public class Enemy extends Shooter{
    //Enemy static names
    public static final String SENTRY = "SENTRY";
    public static final String JUGGERNAUT = "JUGGERNAUT";
    public static final String SPINNER = "SPINNER";

    public float fX,fY; //position to move to before starting the loop
    protected boolean loopMode = false;

    private int loopTimer = 0; //new timer to control the loop separately
    private Image photoDamaged; //Unique photo


    /***
     * Credit : Kevin
     * @param x0 initPos x
     * @param y0 initPos y
     * @param fX finalPos x
     * @param fY finalPos y
     * @param handler
     */
    private Enemy(float x0, float y0, float fX, float fY, Handler handler){
        super(x0,y0, handler);
        this.fX = fX;
        this.fY = fY;

        lookDirection = new float[]{0,1};

        /**
         * get move direction from final point -
         */
        if(fX - x != 0)
            speed[0] = (fX - x)/(float)Math.sqrt(Math.pow(fX - x,2) + Math.pow(fY - y,2));
        else
            speed[0] = 0;
        if(fY - y != 0)
            speed[1] = (fY - y)/(float)Math.sqrt(Math.pow(fX - x,2) + Math.pow(fY - y,2));
        else
            speed[1] = 0;


    }

    /***
     * Credit : Both
     * @param name unit name (use Enemy static values)
     * @param x0 initPos x
     * @param y0 initPos y
     * @param fX finalPos x
     * @param fY finalPos y
     * @param handler
     */
    public Enemy(String name, int x0, int y0 , int fX, int fY, Handler handler){
        this(x0,y0,fX,fY, handler);
        switch(name){
            case "SENTRY":
                width = 40;
                height =40;
                speed[0] *= 2;
                speed[1] *= 2;

                health=1;
                maxHealth = health;

                for(int i = 0; i < projectiles.length; i++)
                    projectiles[i] = new Projectile(5,10,2,new RectangleShape(0,0,5,10), handler);
                color = Color.BLUE;

                this.name = name;
                this.photo=ImageLoader.sentryImage;
                photoDamaged = ImageLoader.sentryDamaged;

                shape = new RectangleShape((int)x,(int)y,width,height);
                break;
            case "JUGGERNAUT":
                break;
            case "SPINNER":
                width = 60;
                height = 60;
                speed[0] *= 1;
                speed[1] *= 1;
                health= 20;
                maxHealth = health;
                for(int i = 0; i < projectiles.length; i++)
                    projectiles[i] = new Projectile(5,5,1.5f,new CircleShape(5,5,5), handler);

                color = Color.GREEN;

                this.name = name;
                this.photo=ImageLoader.spinnerImage;
                photoDamaged = ImageLoader.spinnerDamaged;

                shape = new CircleShape((int)x + width/2,(int)y + height/2,width);
                break;

        }
    }

    public static Enemy bossify(Enemy e){
        e.name = "BOSS";
        e.health = 150;
        e.maxHealth = e.health;
        e.width = 100;
        e.height = 100;

        for(int i = 0; i < e.projectiles.length; i++){
            e.projectiles[i].x = 10;
            e.projectiles[i].y = 10;
            e.projectiles[i].shape.update(e);
        }

        e.shape.update(e);
        return e;
    }

    /***
     * Credit : Kevin
     */
    public void update(){

        move();
        behaviorUpdate();
        innerTimer += Handler.DELAY;
        loopTimer += Handler.DELAY;
        shape.update(this);
    }

    /***
     * Credit : Kevin
     */
    @Override
    public void whenCollided(Entity entity) {
        switch (entity.getEntityTypeName()){
            case "ball" :
                health -= Ball.BALL_DAMAGE;
                innerTimer = 0;

                break;
            case "stickprojectile":
                Projectile p = (Projectile) entity;
                health -= p.damage;
                innerTimer = 0;
                break;
            default :
                break;
        }

    }

    @Override
    public void fire() {
        switch(name){
            case "SENTRY":
                super.fire();
                break;
            case "SPINNER":
                projectiles[projectileIndex].fire(this, new float[]{1,0});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{1,1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{0,1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{-1,1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{-1,0});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{-1,-1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{0,-1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{1,-1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                break;
            case "BOSS":
                projectiles[projectileIndex].fire(this, new float[]{1,0});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{1,1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{0,1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{-1,1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{-1,0});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{-1,-1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{0,-1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                projectiles[projectileIndex].fire(this, new float[]{1,-1});
                projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
                break;
        }

    }

    /***
     * Credit : Rached
     * @param g
     */
    @Override
    public void startDestructionSequence(Graphics g) {
        if (animationIndex<maxAnimationIndex){
            g.drawImage(ImageLoader.explosionAnimation[animationIndex],(int)x-width,
                    (int)y-height,
                    width*4,
                    height*4, handler.getView());
            animationIndex++;
        }
        else{
            handler.removeEnemy(this);
        }
    }

    @Override
    public String getEntityTypeName() {
        return "enemy";
    }

    /***
     * Credit : Kevin
     */
    public void move(){ //eventually move to abstract

        x += speed[0];
        y += speed[1];

        if(!loopMode && Math.abs(fX - x) <= 2f && Math.abs(fY - y) <= 2f) { //home reached
            loopMode = true;
            loopTimer = 0; //soft reset the timer to control the loop easily
        }


    }

    /***
     * Defines loop behavior by Enemy name
     * Credit : Kevin
     */
    public void behaviorUpdate(){
        if(loopMode){
            switch(name){
                case "SENTRY":
                    //Does left right and fires every second
                    if(loopTimer % 2000 < 1000){
                        speed[0] = -0.75f;
                        speed[1] = 0;

                    }else{
                        speed[0] = 0.75f;
                        speed[1] = 0;
                    }

                    if(loopTimer % 1000 < 8)
                        fire();
                    break;

                case "JUGGERNAUT":
                    break;
                case "SPINNER":
                    speed[0] = 0;
                    speed[1] = 0;
                    //Does up, stationary, down, stationary
                    if(2000 < loopTimer % 8000 && loopTimer % 8000 < 4000){
                        speed[0] = 0;
                        speed[1] = -0.5f;

                    }

                    if(6000 < loopTimer % 8000 && loopTimer % 8000 < 8000){
                        speed[0] = 0;
                        speed[1] = 0.5f;
                    }

                    if(loopTimer% 4000 == 0)
                        fire();
                    break;
                case "BOSS":
                    speed[0] = 0;
                    speed[1] = 0;

                    if(loopTimer % 2000 < 1001){
                        if(loopTimer % 330 < 8)
                            fire();
                    }
                    break;
                default:
                    break;
            }


        }

    }

    @Override
    public void drawEntity(Graphics g){
        if (health>0){
            if(innerTimer > 80)
                g.drawImage(photo,(int)x,(int)y,width,height, handler.getView());
            else
                g.drawImage(photoDamaged,(int)x,(int)y,width,height, handler.getView());

            g.setColor(Color.GREEN);
            g.fillRect((int)x,(int)y - 10, (int)( (health/(float)maxHealth) * width),5);
            g.setColor(Color.RED);
            g.fillRect((int)x + (int)( (health/(float)maxHealth) * width),(int)y - 10,
                        (int)( (maxHealth - health)/(float)maxHealth * width),5);

        }
        else startDestructionSequence(g);


    }

}
