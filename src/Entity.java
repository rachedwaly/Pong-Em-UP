import java.awt.*;
import java.util.Random;

public abstract class Entity {

    protected int x,y,width,height;
    static final int HEIGHT=500; //height of the game
    static final int WIDTH=300; //width of the game
    protected  int speed; //speed in pixels /10ms
    protected boolean orientation=false; //the orientation of the object : TODO which is which ?
    public Color color = Color.BLACK;
    protected String name;


    public boolean isOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    public Entity(){
        this(Main.random.nextInt(WIDTH),Main.random.nextInt(HEIGHT),
                Main.random.nextInt(30),Main.random.nextInt(30),true);
    }

    public Entity(int x,int y){
        this.x=x;
        this.y=y;
        this.width=30;
        this.height=30;
        this.speed = 1;
    }

    public Entity(int x,int y, int w, int h){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        this.speed = 1;
    }

    public Entity(int x,int y, int w, int h,boolean orientation){
        this.orientation=orientation;
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        this.speed = 1;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }

}
