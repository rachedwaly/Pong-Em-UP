import java.awt.*;
import java.util.Vector;

public class Enemy extends Entity{ //Eventuellement transformer en LineEnemy
    public int fX,fY; //pos finale de l'objet
    public double distance;
    public float[] directionVector = new float[2]; //vecteur de pos initiale vers pos finale
    public boolean destReached = false;

    public Enemy(){

        super();
        fX = x;
        fY = y;
        distance = 0;
        directionVector[0] = 0;
        directionVector[1] = 0;
        //Normalize
        speed = 1;

    }

    public Enemy(int x,int y,int fX, int fY,Color color){
        super(x,y);
        this.fX = fX;
        this.fY = fY;
        distance = Math.sqrt(Math.pow((fX - x),2) + Math.pow((fY - y),2));
        //Normalize
        directionVector[0] = (fX - x) / (float)distance;
        directionVector[1] = (fY - y) / (float)distance;

        speed = 1;
        this.color = color;
    }

    public Enemy(int x,int y, int w, int h,int fX, int fY,Color color){
        super(x, y, w, h);
        this.fX = fX;
        this.fY = fY;
        distance = Math.sqrt(Math.pow((fX - x),2) + Math.pow((fY - y),2));
        directionVector[0] = (fX - x) / (float)distance;
        directionVector[1] = (fY - y) / (float)distance;
        //Normalize
        speed = 1;
        this.color = color;
    }

    public void move(){
        if(!destReached){
            x += (int)(directionVector[0] * speed);
            y += (int)(directionVector[1] * speed);
            if(x >= fX && y >= fY)
                destReached = true;
        }

    }

}
