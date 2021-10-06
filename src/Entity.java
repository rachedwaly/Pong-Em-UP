import java.awt.*;
import java.util.ArrayList;

public abstract class Entity {
    protected float x,y;
    protected int width,height;
    static final int HEIGHT=PlayGround.HEIGHT; //height of the game
    static final int WIDTH=PlayGround.WIDTH; //width of the game

    static final int SCROLLSPEED = 1;
    protected  float[] speed = new float[2];
    protected int[] lookDirection;
    protected boolean orientation=false; //the orientation of the object //false for vertical
    // objects
    public Color color;
    protected String name;
    protected int innerTimer = 0;

    public Entity(){
        this(Main.random.nextInt(WIDTH),Main.random.nextInt(HEIGHT),
                Main.random.nextInt(30),Main.random.nextInt(30),true);
    }

    public Entity(int x,int y){
        this(x,y,0,0);

    }

    public Entity(int x,int y, int w, int h){
        this(x,y,w,h,false);

    }

    public Entity(int x,int y, int w, int h,boolean orientation){
        this.orientation=orientation;
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        color = Color.BLACK;
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

    /***
     * Updates the entity based on the current game timer : movement, color etc
     */
    public abstract void update();

    /***
     * Updates the entity when colliding with another entity
     * THIS METHOD ONLY MODIFIES THE OBJECT ON WHICH IT IS CALLED
     * @param entity the other object
     */
    public abstract void whenCollided(Entity entity);

    /***
     * Alternative to instanceof to ease switch methods
     * @return entity class name in string
     */
    public abstract String getEntityTypeName();

    /***
     *
     * @return Rectangle which defines bounds (even ball is a rectangle)
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,width,height);
    }

    /***
     * Entity's paint method
     * @param g
     */
    public abstract void drawEntity(Graphics g);

    /***
     * Defines the sides of an entity for the ball to rebound correctly when colliding with an object
     * @return
     */
    public abstract ArrayList<PhysicalBoundarie> getPhysicalBoundaries();


}
