import shape.CustomRectangle;

public abstract class Shooter extends Entity{
    protected int health;
    protected int maxHealth;
    public int projectileIndex = 0;
    public boolean canShoot;
    static final int PROJECTILEBUFFER = 50; //pre allocated space for projectiles
    public Projectile[] projectiles = new Projectile[PROJECTILEBUFFER]; //Ten buffered projectiles, 11 is empty

    public Shooter(){
        super();
    }

    public Shooter(int x, int y){
        super(x,y);
    }

    public void fire(){
        projectiles[projectileIndex].fire(this);
        projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
    }

    public void whenCollided(Entity entity){
        switch(entity.getEntityTypeName()){
            case "projectile" :
                Projectile p = (Projectile) entity;
                this.health -= p.damage;
                break;
        }
    }

}
