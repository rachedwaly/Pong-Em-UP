package Entities;
import shape.CustomShape;
import Game.*;
import shape.RectangleShape;

import java.awt.*;
import java.util.Random;

public abstract class Entity {
    protected float x,y;
    protected int width,height;
    public static final int HEIGHT= Model.HEIGHT; //height of the game
    public static final int WIDTH= Model.WIDTH; //width of the game
    protected Image photo;

    static final int SCROLLSPEED = 1;
    protected float[] speed = new float[2];
    protected int[] lookDirection=new int[2];

    // objects
    protected CustomShape shape;
    public Color color;
    protected String name;

    private boolean active = true;
    protected int innerTimer = 0;

    public Entity(){
        this(Model.random.nextInt(WIDTH), Model.random.nextInt(HEIGHT),
                Model.random.nextInt(30), Model.random.nextInt(30));
    }

    public Entity(int x,int y,Model model){
        this(x,y,0,0,model);

    }

    public Entity(int x,int y, int w, int h,Model model){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        this.color = Color.BLACK;
        this.model=model;
        color = Color.BLACK;

        shape = new RectangleShape(x,y,w,h);
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
    //
    public CustomShape getShape(){
        return shape;
    };

    /***
     * Defines the sides of an entity for the ball to rebound correctly when colliding with an object
     * @return
     */
    public float[] getNormalHit(Entity e){
        float[] center = new float[]{e.x + e.width/2f, e.y + e.height/2f};
        if(center[0] - x < center[1] - y) {//hit vertical wall
            if(center[0] - x < 0)//Left
                return new float[]{-1,0};
            else
                return new float[]{1,0};

        }else{//Horizontal
            if(center[1] - y < 0)//Up
                return new float[]{0,-1};
            else
                return new float[]{0,1};
        }
    }

    /***
     * Entity's paint method
     * @param g
     */
    protected abstract void drawEntity(Graphics g);

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

}
