package Entities;
import Game.*;

import java.awt.*;

/***
 * Parent class of Stick and Enemies, to centralize projectile initialisation
 * Credit : Mostly Rached
 */
public abstract class Shooter extends Entity{


    protected int health;



    protected int maxHealth;
    public int projectileIndex = 0;
    public boolean canShoot;
    /**
     * pre allocated space for projectiles
     */
    static final int PROJECTILEBUFFER = 50;
    /**
     * //Ten buffered projectiles, 11 is empty
     */
    public Projectile[] projectiles = new Projectile[PROJECTILEBUFFER];
    /**
     * this will be used to render the death animation or any animation later on
     *
     */
    protected int animationIndex=1;
    static final int maxAnimationIndex=9;





    public Shooter(Handler handler){
        super(handler);
    }

    public Shooter(float x, float y, Handler handler){
        super(x,y, handler);
    }

    public void fire(){
        projectiles[projectileIndex].fire(this,lookDirection);
        projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
    }

    public abstract void startDestructionSequence(Graphics g);

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health<=maxHealth) this.health = health;
        else this.health=maxHealth;
    }

}
