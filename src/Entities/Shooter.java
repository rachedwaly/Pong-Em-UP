package Entities;
import Game.*;


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

    public Shooter(int x, int y,Model model){
        super(x,y,model);
    }

    public void fire(){
        projectiles[projectileIndex].fire(this);
        projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
    }

    public abstract void whenCollided(Entity entity);



}
