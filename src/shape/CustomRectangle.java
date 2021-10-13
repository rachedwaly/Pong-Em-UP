package shape;

import java.awt.*;
import Entities.*;

public class CustomRectangle extends CustomShape {
    public int width, height;
    public Rectangle shapeToRec;
    public CustomRectangle(int x, int y, int width, int height){
        super(x,y);
        this.width = width;
        this.height = height;

        shapeToRec = new Rectangle(x,y,width,height);
    }

    @Override
    public void update(Entity source) {
        x = (int)source.getX();
        y = (int)source.getY();
        width = source.getWidth();
        height = source.getHeight();
    }

    public boolean intersects(CustomRectangle r){
        return shapeToRec.intersects(r.shapeToRec);
    }

    public boolean intersects(CircleShape cs){
        return cs.intersects(this);
    }

}
