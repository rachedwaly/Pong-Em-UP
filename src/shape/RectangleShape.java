package shape;

import java.awt.*;
import Entities.*;

public class RectangleShape extends CustomShape {
    public int width, height;
    public Rectangle shapeToRec;
    public RectangleShape(int x, int y, int width, int height){
        super(x,y);
        this.width = width;
        this.height = height;

        shapeToRec = new Rectangle(x,y,width,height);
    }

    @Override
    public void update(Entity source) {
        x = shapeToRec.x = (int)source.getX();
        y = shapeToRec.y = (int)source.getY();
        width = shapeToRec.width = source.getWidth();
        height = shapeToRec.height = source.getHeight();


    }

    @Override
    public boolean intersects(PolygonShape cs) {
        return false;
    }

    public boolean intersects(RectangleShape r){

        return shapeToRec.intersects(r.shapeToRec);
    }

    public boolean intersects(CircleShape cs){

        return cs.intersects(this);
    }

    public float[] getCenter(){
        return new float[]{x + width/2,y + height/2};
    }
}
