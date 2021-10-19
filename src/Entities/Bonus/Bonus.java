package Entities.Bonus;

import Entities.Ball;
import Entities.Entity;
import Entities.Stick;
import Game.Model;

import java.awt.*;

public abstract class Bonus extends Entity {
    protected Model model;

    protected Stick stick;

    protected int delay;


    /**
     * this boolean will indicate if the stick got the bonus or not
     */
    public boolean acquired=false;

    /**
     * credits: Rached
     * @param name name of the bonus
     * @param x spawn's x coordinate
     * @param y spawn's y corrdiante
     * @param delay bonus period of activation
     * @param stick reference to the stick
     * @param model referece to the model
     */
    public Bonus(String name, float x, float y,int delay,Stick stick, Model model){
        super((int)x,(int)y, model);
        this.stick=stick;
        innerTimer=0;
        this.name=name;
        this.x=x;
        this.y=y;
        this.width=40;
        this.delay=delay;
        this.height=40;
        this.speed[0]=0;
        this.speed[1]=2;
        this.model = model;
        color=Color.PINK;
    }



    public void move() {
        y+=speed[1];
        shape.update(this);
        if (y>600){
            model.removeBonus(this);
        }
    }

    @Override
    public void whenCollided(Entity entity) {

            if (entity instanceof Stick || entity instanceof Ball) {
                if (!acquired) {

                    acquired = true;
                    applyBonus();
            }
        }
    }

    @Override
    public String getEntityTypeName() {
        return "bonus";
    }


    @Override
    public void update() {
        if (!acquired) move();
        else{
            decrementDelay();
            if (delay<=0){
                removeBonus();
                model.removeBonus(this);
            }
        }
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void decrementDelay(){
        this.delay-= Model.DELAY;
    }

    protected abstract void applyBonus();

    /**
     * This method will handle removing the bonus when the time of activation of this bonus has
     * passed
     */
    protected abstract void removeBonus();


    /**
     * @param g
     * @param x
     * @param y
     */
    public abstract void drawInStatusBar(Graphics g,int x, int y);

    public boolean isAcquired() {
        return acquired;
    }

}
