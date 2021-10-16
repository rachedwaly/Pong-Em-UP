package shape;

import Entities.Entity;

import java.awt.*;
import java.util.ArrayList;

public class PolygonShape extends CustomShape{
    public float[][] pointList;
    public int width, height;

    public PolygonShape(int x, int y){
        super(x,y);
        pointList = new float[3][2];
        pointList[0] = new float[]{x,y};
        pointList[1] = new float[]{x + width,y};
        pointList[2] = new float[]{x + width/2,y + height};
    }

    @Override
    public void update(Entity source) {
        x = (int)source.getX();
        y = (int)source.getY();
        width = source.getWidth();
        height = source.getHeight();

        pointList[0] = new float[]{x,y};
        pointList[1] = new float[]{x + width,y};
        pointList[2] = new float[]{x + width/2,y + height};
    }

    @Override
    public boolean intersects(PolygonShape cs) {
        return false;
    }

    @Override
    public boolean intersects(RectangleShape cs) {

        return false;
    }

    @Override
    public boolean intersects(CircleShape cs) {
        return false;
    }

    public static boolean segmentIntersect(float[] p1, float[] p2,
                                        float[] p3, float[] p4){
        int d1 = clockDirection(p1, p2, p3);
        int d2 = clockDirection(p1, p2, p4);
        int d3 = clockDirection(p3, p4, p1);
        int d4 = clockDirection(p3, p4, p2);

        if (d1 != d2 && d3 != d4)
            return true;
        if (d1 == 0 && pointOnLine(p3,p1,p2))
            return true;
        if (d2 == 0 && pointOnLine(p4,p1,p2))
            return true;
        if (d3 == 0 && pointOnLine(p1,p3,p4))
            return true;
        if (d4 == 0 && pointOnLine(p2,p3,p4))
            return true;
        return false;
    }

    public static int clockDirection(float[] a, float[] b, float[] c){
        float res = (b[1] - a[1]) * (c[0] - b[0]) - (b[0] - a[0]) * (c[1] - b[1]);
        if(res == 0)
            return 0;
        else{
            if(res > 0)
                return 1;
            else
                return -1;
        }

    }

    @Override
    public float[] getCenter() {
        return new float[0];
    }
}
