import java.awt.*;

public class Entity {
    protected int x,y,width,height;
    final int HEIGHT=500; //height of the game
    final int WIDTH=300; //width of the game
    protected  int speed;
    protected boolean orientation=false; //the orientation of the object
    protected String name;


    public boolean isOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
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
