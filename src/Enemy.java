import java.awt.*;
import java.util.ArrayList;

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

        speed[0]=1;
        speed[1]=1;

    }


    public Enemy(int x,int y,int fX, int fY,Color color){
        super(x,y);
        width=30;
        height=30;
        this.fX = fX;
        this.fY = fY;
        distance = Math.sqrt(Math.pow((fX - x),2) + Math.pow((fY - y),2));
        //Normalize
        directionVector[0] = (fX - x) / (float)distance;
        directionVector[1] = (fY - y) / (float)distance;

        speed[0] = 1;
        speed[1]=1;
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
        speed[0] = 1;
        speed[1]=1;
        this.color = color;
    }

    public void move(){
        if(!destReached){
            x += (int)(directionVector[0] * speed[0]);
            y += (int)(directionVector[1] * speed[1]);
            if(x >= fX && y >= fY) {
                destReached = true;
                speed[0]=0;
                speed[1]=0;
            }

        }
    }
    public void attack(){

    }

    @Override
    public void drawEntity(Graphics g){
        g.drawRect(x,y,width,height);
    }

    @Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        PhysicalBoundarie c1=new PhysicalBoundarie(x,y,1,height,false); //left side
        PhysicalBoundarie c2=new PhysicalBoundarie(x+width-1,y,1,height,false); // right side
        PhysicalBoundarie c3=new PhysicalBoundarie(x,y,width,1,true); //top side
        PhysicalBoundarie c4=new PhysicalBoundarie(x,y+height-1,width,1,true); //bottom side
        ArrayList <PhysicalBoundarie> list=new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        return list;
    }
}
