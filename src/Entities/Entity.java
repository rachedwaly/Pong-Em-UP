package Entities;
import shape.*;
import Game.*;
import shape.RectangleShape;

import java.awt.*;

/***
 * Abstract class which every collidable object inherits from
 * Credit : Both
 */
public abstract class Entity {
    public static final int HEIGHT= Handler.HEIGHT; //max height of the game
    public static final int WIDTH= Handler.WIDTH; //max width of the game

    public Handler handler;
    /**
     * float position the converted to int
     */
    protected float x,y;

    protected int width,height;
    protected CustomShape shape; //Custom collider
    /**
     * directionVector (usually)
     */
    protected float[] speed = new float[2];
    protected float[] lookDirection=new float[2];

    protected Image photo;

    public Color color;
    protected String name;

    /**
     * tracks time for object loop behavior and animations
     */
    public int innerTimer;

    /**
     * Default constructor
     * @param handler
     */
    public Entity(Handler handler){
        this(Handler.random.nextInt(WIDTH), Handler.random.nextInt(HEIGHT),
                Handler.random.nextInt(30), Handler.random.nextInt(30), handler);
    }

    public Entity(float x, float y, Handler handler){
        this(x,y,0,0, handler);

    }

    /***
     * Credit : Both
     * @param x x pos
     * @param y y pos
     * @param w width
     * @param h height
     * @param handler handler which it is attached to
     */
    public Entity(float x, float y, int w, int h, Handler handler){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        this.color = Color.BLACK; //default color
        this.handler = handler;
        color = Color.BLACK;

        innerTimer = 81; //0 -> 80 is reserved for blinking animations
        shape = new RectangleShape((int)x,(int)y,w,h);
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
     * @param entity the other object in the collision
     */
    public abstract void whenCollided(Entity entity);

    /***
     * Alternative to instanceof to ease switch methods
     * @return entity class name in lowercase string
     */
    public abstract String getEntityTypeName();

    /***
     * Returns the custom collider attached to the entity
     * @return
     */
    //
    public CustomShape getShape(){
        return shape;
    };

    /***
     * Entity's paint method
     * @param g
     */
    public abstract void drawEntity(Graphics g);

    /***
     * Prints various information about an entity
     * Useful for maintenance and debugging
     * Credit : Kevin
     */
    public void debugLog(){
        String entity = "type : " + getEntityTypeName() +
                        "\npos : (" + x + ", " + y + ")" +
                        "speed : (" + speed[0] + ", " + speed[1] + ")";

        String collider = "shape type : ";
        if(shape instanceof RectangleShape){
            RectangleShape rs = (RectangleShape) shape;
            collider += "rectangle";
            collider += "\npos : (" + rs.x + ", " + rs.y + ")";
            collider += "\nwh : (" + rs.width + ", " + rs.height + ")";
        }else{
            CircleShape cs = (CircleShape) shape;
            collider += "circle";
            collider += "\npos : (" + cs.x + ", " + cs.y + ")";
            collider += "\nradius : " + cs.radius;
        }
        System.out.println(entity);
        System.out.println(collider);
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
