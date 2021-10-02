import java.awt.*;
import java.util.ArrayList;

public class Wall extends Entity{
    public Wall(int x,int y,int w,int h){
        super(x,y,w,h);

    }

    @Override
    public void update(ArrayList<Entity> eList) {

    }

    @Override
    public void whenCollided(Entity entity) {
        //play sound
    }

    @Override
    public void drawEntity(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect((int)x,(int)y,width,height);

    }

    @Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        return null;
    }


}
