package shape;

import Entities.*;

/***
 * Defines collider through various attributes
 * Credit : Kevin
 */
public abstract class CustomShape{
    public int x,y;

    public CustomShape(int x, int y){
        this.x = x;
        this.y = y;
    }

    /***
     * Updates the collider according to the new coordinates/properties of entity
     * Credit : Kevin
     * @param source
     */
    public abstract void update(Entity source);

    /***
     * Tests if another shape intersects with it
     * Credit : Kevin
     * @param cs
     * @return
     */
    public boolean intersects(CustomShape cs) {
        if(cs instanceof RectangleShape)
            return intersects((RectangleShape) cs);
        if(cs instanceof CircleShape)
            return intersects((CircleShape) cs);
        if(cs instanceof PolygonShape)
            return intersects((PolygonShape) cs);
        System.out.println("Unidentified shape");
        return false;
    }

    //Polymorphism
    public abstract boolean intersects(PolygonShape cs);

    public abstract boolean intersects(RectangleShape cs);

    public abstract boolean intersects(CircleShape cs);

    public abstract float[] getCenter();

    /***
     * returns if a point determined colinear to p1 and p2 is on said segment p1p2
     * Credit : Kevin
     * @param askPoint the point we're testing
     * @param p1
     * @param p2
     * @return
     */
    public static boolean pointOnLine(float[] askPoint, float[] p1, float[] p2){
        return Math.min(p1[0],p2[0]) <= askPoint[0] && askPoint[0] <= Math.max(p1[0],p2[0]) &&
                Math.min(p1[1],p2[1]) <= askPoint[1] && askPoint[1] <= Math.max(p1[1],p2[1]);
    }
}





