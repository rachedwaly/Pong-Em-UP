package Entities;
import Game.*;
import shape.CircleShape;
import shape.RectangleShape;

import java.awt.*;

public class Enemy extends Shooter{ //Eventuellement transformer en LineEnemy
    public int fX,fY; //pos "finale" de l'objet, ou sa loop de comportement commence
    protected boolean loopMode = false; //false : se deplace vers (fX,fY) || true : effectue sa loop de behavior
    public static final String SENTRY = "SENTRY";
    public static final String JUGGERNAUT = "JUGGERNAUT";
    public static final String SPINNER = "SPINNER";
    private int loopTimer = 0;
    private Image photoDamaged;



    private Enemy(int x0, int y0, int fX, int fY,Model model){
        super(x0,y0,model);
        this.fX = fX;
        this.fY = fY;

        lookDirection = new float[]{0,1};

        if(fX - x != 0)
            speed[0] = (fX - x)/(float)Math.sqrt(Math.pow(fX - x,2) + Math.pow(fY - y,2));
        else
            speed[0] = 0;
        if(fY - y != 0)
            speed[1] = (fY - y)/(float)Math.sqrt(Math.pow(fX - x,2) + Math.pow(fY - y,2));
        else
            speed[1] = 0;


    }

    public Enemy(String name, int x0, int y0 , int fX, int fY,Model model){
        this(x0,y0,fX,fY,model);
        switch(name){
            case "SENTRY":
                width = 40;
                height =40;
                speed[0] *= 2;
                speed[1] *= 2;
                health=1;
                for(int i = 0; i < projectiles.length; i++)
                    projectiles[i] = new Projectile(5,20,10,2,model);

                color = Color.BLUE;

                this.name = name;
                this.photo=model.getPhoto("sentry");
                photoDamaged = model.getPhoto("sentryRed");

                shape = new RectangleShape((int)x,(int)y,width,height);
                break;
            case "JUGGERNAUT":
                break;
            case "SPINNER":
                width = 60;
                height = 60;
                speed[0] *= 1;
                speed[1] *= 1;
                health= 1000;
                for(int i = 0; i < projectiles.length; i++)
                    projectiles[i] = new Projectile(5,5,10,1.5f,model);
                //TODO : changer le system de tir dans fire par entite
                color = Color.GREEN;

                this.name = name;
                this.photo=model.getPhoto("sentry");
                photoDamaged = model.getPhoto("sentryRed");

                shape = new CircleShape((int)x + width/2,(int)y + height/2,width);
                break;

        }
    }


    public void update(){

        move();
        behaviorUpdate();
        innerTimer += Model.DELAY;
        loopTimer += Model.DELAY;
        shape.update(this);
    }

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
        }

    }

    @Override
    public void startDestructionSequence(Graphics g) {
        if (animationIndex<=maxAnimationIndex){
            g.drawImage(model.getPhoto(Integer.toString(animationIndex)+"death"),(int)x-width,
                    (int)y-height,
                    width*4,
                    height*4,model.getView());
            animationIndex++;
        }
        else{
            model.removeEnemy(this);
        }
    }

    @Override
    public String getEntityTypeName() {
        return "enemy";
    }

    public void move(){ //eventually move to abstract

        x += speed[0];
        y += speed[1];

        if(!loopMode && Math.abs(fX - x) <= 2f && Math.abs(fY - y) <= 2f) { //home reached
            loopMode = true;
            loopTimer = 0; //soft reset the timer to control the loop easily
        }


    }

    public void behaviorUpdate(){
        if(loopMode){
            switch(name){
                case "SENTRY":
                    if(loopTimer % 2000 < 1000){
                        speed[0] = -1;
                        speed[1] = 0;

                    }else{
                        speed[0] = 1;
                        speed[1] = 0;
                    }

                    if(loopTimer % 800 == 0)
                        fire();
                    break;

                case "JUGGERNAUT":
                    break;
                case "SPINNER":
                    speed[0] = 0;
                    speed[1] = 0;
                    if(2000 < loopTimer % 8000 && loopTimer % 8000 < 4000){
                        speed[0] = 0;
                        speed[1] = 0;

                    }

                    if(6000 < loopTimer % 8000 && loopTimer % 8000 < 8000){
                        speed[0] = 0;
                        speed[1] = 0;
                    }

                    if(loopTimer% 4000 == 0)
                        fire();
            }
        }

    }

    @Override
    public void drawEntity(Graphics g){
        if (health>0){
            /*if(innerTimer > 80)
                g.drawImage(photo,(int)x,(int)y,width,height,model.getView());
            else
                g.drawImage(photoDamaged,(int)x,(int)y,width,height,model.getView());*/

            g.setColor(color);
            g.fillOval((int)x,(int)y,width,height);
            g.drawString(Integer.toString(health),(int)x + width/2,(int)y - 10);
        }
        else startDestructionSequence(g);


    }

}
