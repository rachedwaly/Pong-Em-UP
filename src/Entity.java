import java.awt.*;
import java.util.ArrayList;

public abstract class Entity {
    protected float x,y;
    protected int width,height;
    static final int HEIGHT=PlayGround.HEIGHT; //height of the game
    static final int WIDTH=PlayGround.WIDTH; //width of the game
    static final int PROJECTILEBUFFER = 20; //pre allocated space for projectiles
    static final int SCROLLSPEED = 1;
    protected  float[] speed = new float[2];
    protected int[] lookDirection;
    protected boolean orientation=false; //the orientation of the object //false for vertical
    // objects
    public Color color = Color.BLACK;
    protected String name;
    protected int innerTimer = 0;

    public Entity(){
        this(Main.random.nextInt(WIDTH),Main.random.nextInt(HEIGHT),
                Main.random.nextInt(30),Main.random.nextInt(30),true);
    }

    public Entity(int x,int y){
        this.x=x;
        this.y=y;
        this.width=0;
        this.height=0;

    }

    public Entity(int x,int y, int w, int h){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;

    }

    public Entity(int x,int y, int w, int h,boolean orientation){
        this.orientation=orientation;
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;

    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public abstract void update(ArrayList<Entity> eList);

    public abstract void whenCollided(Entity entity);

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,width,height);
    }

    public abstract void drawEntity(Graphics g);

    public abstract ArrayList<PhysicalBoundarie> getPhysicalBoundaries();


}
