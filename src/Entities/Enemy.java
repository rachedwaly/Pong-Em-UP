package Entities;
import Entities.Shooter;
import shape.CustomRectangle;
import Game.*;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Shooter { //Eventuellement transformer en LineEnemy
    public int fX,fY; //pos "finale" de l'objet, ou sa loop de comportement commence
    protected boolean loopMode = false; //false : se deplace vers (fX,fY) || true : effectue sa loop de behavior
    public static final String SENTRY = "SENTRY";
    private int loopTimer = 0;
    private Image photoDamaged;



    private Enemy(int x0, int y0, int fX, int fY,Model model){
        super(x0,y0,model);
        this.fX = fX;
        this.fY = fY;

        lookDirection = new int[]{0,1};

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
                    projectiles[i] = new Projectile(5,20,10,new float[]{2f,2f},model);
                color = Color.BLUE;
                this.name = name;
                this.photo=model.getPhoto("sentry");
                photoDamaged = model.getPhoto("sentryRed");
                shape = new RectangleShape((int)x,(int)y,width,height);
                break;
            case "JUGGERNAUT":
                break;
            case "SPINNER":
                break;

        }
    }


    public void update(){

        move();
        behaviorUpdate();
        innerTimer += Model.DELAY;
        loopTimer += Model.DELAY;
    }

    @Override
    public void whenCollided(Entity entity) {
        System.out.println("le collision");
        switch (entity.getEntityTypeName()){
            case "ball" :
                health -= 100;
                break;
            case "projectile":
                Projectile p = (Projectile) entity;
                health -= p.damage;

                break;
            case "Enemy":

            default :
                break;
        }


        innerTimer = 0;
    }

    @Override
    public CustomRectangle getBounds(){
        return new CustomRectangle((int)x,(int)y,width,height);
    };

    @Override
    public String getEntityTypeName() {
        return "enemy";
    }

    public void move(){ //eventually move to abstract


        x += speed[0];
        y += speed[1];

        if(!loopMode && Math.abs(fX - x) <= 2f && Math.abs(fY - y) <= 2f) { //home reached
            loopMode = true;
            loopTimer = 4000; //soft reset the timer to control the loop easily
        }
        shape.update(this);

    }

    public void behaviorUpdate(){
        if(loopMode){
            switch(name){
                case "SENTRY":

                    if(loopTimer % 2000 < 1000){
                        speed[0] = - 1;
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
                    break;

            }
        }

    }

    @Override
    public void drawEntity(Graphics g){
        if (health>0){
            if(innerTimer > 80)
                g.drawImage(photo,(int)x,(int)y,width,height,model.getView());
            else
                g.drawImage(photoDamaged,(int)x,(int)y,width,height,model.getView());
            g.drawString(Integer.toString(health),(int)x + width/2,(int)y - 10);
        }
        else if (animationIndex<=maxAnimationIndex){
            g.drawImage(model.getPhoto(Integer.toString(animationIndex)+"death"),(int)x-width,
                    (int)y-height,
                    width*4,
                    height*4,model.getView());
            animationIndex++;
        }
        else{
            model.removeEntity(this);
        }


    }



}
