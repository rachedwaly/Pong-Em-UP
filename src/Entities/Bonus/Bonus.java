package Entities.Bonus;

import Entities.Entity;
import Entities.Stick;
import Game.Model;

import java.awt.*;

public abstract class Bonus extends Entity {
    protected Model model;

    protected Stick stick;

    protected int delay;
    protected boolean acquired=false; //this boolean will indicate if the stick got the bonus or not

    public Bonus(String name, float x, float y,int delay,Stick stick, Model model){
        super((int)x,(int)y, model);
        this.model = model;
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

            if (entity instanceof Stick) {
                if (!acquired) {

                    acquired = true;
                    applyBonus();
            }
        }
    }

    @Override
    public String getEntityTypeName() {
        return name;
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

    protected abstract void removeBonus();

}
