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
    static final int PROJECTILEBUFFER = 50; //pre allocated space for projectiles
    public Projectile[] projectiles = new Projectile[PROJECTILEBUFFER]; //Ten buffered projectiles, 11 is empty
    protected int animationIndex=1; //this will be used to render the death animation or any
    // animation later on
    static final int maxAnimationIndex=9;





    public Shooter(Model model){
        super(model);
    }

    public Shooter(int x, int y, Model model){
        super(x,y, model);
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
