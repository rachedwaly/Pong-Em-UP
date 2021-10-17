package Entities;
import shape.*;
import Game.*;
import shape.RectangleShape;

import java.awt.*;

public abstract class Entity {
    public Model model;
    protected float x,y;


    protected int width,height;
    public static final int HEIGHT= Model.HEIGHT; //height of the game
    public static final int WIDTH= Model.WIDTH; //width of the game
    protected Image photo;

    static final int SCROLLSPEED = 1;
    protected float[] speed = new float[2];
    protected float[] lookDirection=new float[2];


    // objects
    protected CustomShape shape;
    public Color color;
    protected String name;

    protected int innerTimer;

    public Entity(Model model){
        this(Model.random.nextInt(WIDTH), Model.random.nextInt(HEIGHT),
                Model.random.nextInt(30), Model.random.nextInt(30), model);
    }

    public Entity(int x, int y, Model model){
        this(x,y,0,0, model);

    }

    public Entity(int x, int y, int w, int h, Model model){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        this.model = model;
        color = Color.BLACK;
        innerTimer = 81; //0 -> 80 is reserved for blinking animations
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
     * Entity's paint method
     * @param g
     */
    public abstract void drawEntity(Graphics g);

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
