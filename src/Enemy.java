import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Entity{ //Eventuellement transformer en LineEnemy
    public int fX,fY; //pos finale de l'objet
    public boolean destReached = false;

    public Enemy(){

        super();
        fX = x;
        fY = y;

        speed[0] = 0;
        speed[1] = 0;

    }


    public Enemy(int x,int y,int fX, int fY,Color color){
        super(x,y);
        width=30;
        height=30;
        this.fX = fX;
        this.fY = fY;

        if(fX - x != 0)
            speed[0] = (fX - x)/Math.abs(fX - x);
        else
            speed[0] = 0;
        if(fY - y != 0)
            speed[1] = (fY - y)/Math.abs(fY - y);
        else
            speed[1] = 0;
        this.color = color;
    }

    public Enemy(int x,int y, int w, int h,int fX, int fY,Color color){
        super(x, y, w, h);
        this.fX = fX;
        this.fY = fY;

        if(fX - x != 0)
            speed[0] = (fX - x)/Math.abs(fX - x);
        else
            speed[0] = 0;
        if(fY - y != 0)
            speed[1] = (fY - y)/Math.abs(fY - y);
        else
            speed[1] = 0;
        this.color = color;
    }

    public void move(){
            x += speed[0];
            y += speed[1];
            if(Math.abs(fX - x) <= 0.1f && Math.abs(fY - y) <= 0.1f) {
                speed[0]=0;
                speed[1]=0;
            }
    }
    public void attack(){

    }

    @Override
    public void drawEntity(Graphics g){
        g.setColor(this.color);
        g.fillRect(x,y,width,height);
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
