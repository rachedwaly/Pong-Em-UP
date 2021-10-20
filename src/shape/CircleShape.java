package shape;

import AltLib.Vec2Math;
import Entities.*;

/***
 * Circle collider
 * Credit : Kevin
 */
public class CircleShape extends CustomShape{
    //here x,y is center of the circle
    public int radius;
    public String sourceName;
    private float[] normalHit; //Stores the normal hit for the ball

    public CircleShape(int x, int y, int radius){
        super(x,y);
        this.radius = radius;
        normalHit = new float[]{0,0};
    }

    /***
     * Credit : Kevin
     * @param source
     */
    @Override
    public void update(Entity source) {
        sourceName = source.getEntityTypeName();
        radius = source.getWidth()/2;
        x = (int)source.getX() + radius;
        y = (int)source.getY() + radius;

    }

    /***
     * Credit : Kevin
     * @param poly
     * @return
     */
    @Override
    public boolean intersects(PolygonShape poly) {
        if(poly.width == 0 || poly.height == 0)
            return false;
        //TODO LAST : define circle in interior (iterate on triangles)
        if(poly.x <= x && x <= poly.x + poly.width &&
                poly.y <= y && y <= poly.y + poly.height)
            return true;
        return false;
    }

    /***
     * Credit : Kevin + https://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
     * @param rect
     * @return
     */
    @Override
    public boolean intersects(RectangleShape rect){
        if(rect.width == 0 || rect.height == 0){
            return false;
        }
        if(rect.x <= x && x <= rect.x + rect.width &&
            rect.y <= y && y <= rect.y + rect.height)
            return true;

        else{
            float[] topleft = new float[]{rect.x, rect.y};
            float[] topright = new float[]{rect.x + rect.width, rect.y};
            float[] botleft = new float[]{rect.x, rect.y + rect.height};
            float[] botright = new float[]{rect.x + rect.width, rect.y + rect.height};
            return  segmentInCircle(topleft,topright,rect) ||
                    segmentInCircle(topright,botright,rect)||
                    segmentInCircle(botright,botleft,rect)||
                    segmentInCircle(botleft,topleft,rect);
        }

    }

    public boolean pointInCircle(float[] p){
        return Math.sqrt(Vec2Math.distance2(p, new float[]{x,y})) <= radius;
    }

    /**
     * Credit : Kevin
     * @param p1
     * @param p2
     * @return
     */
    public boolean segmentInCircle(float[] p1, float[] p2, CustomShape poly){
        normalHit[0] = - p1[1] + p2[1]; //-b
        normalHit[1] = p1[0] - p2[0]; //a
        if(Vec2Math.dot(p1,poly.getCenter()) < 0){
            //making sure we get the outward normal
            normalHit[0] = -normalHit[0];
            normalHit[1] = -normalHit[1];
        };

        float[] center = new float[]{x,y};
        float[] projection;

        if(Math.abs(p2[0] - p1[0])  <= 0.01 && Math.abs(p2[1] - p1[1])  <= 0.01){ //p1 == p2
            return pointInCircle(p1);
        }
        if(Math.abs(p2[0] - p1[0])  <= 0.01){ //div by 0 exception
            projection = new float[]{p1[0],center[1]};
        }else{
            float a = (p2[1] - p1[1]) / (p2[0] - p1[0]);
            float b = p1[1] - (a * p1[0]);

            projection = new float[]{   (a * center[1] + center[0] - a * b) / (a * a + 1),
                    (a * a * center[1] + a * center[0] + b) / (a * a + 1) };
        }


        if(pointOnLine(projection,p1,p2) && pointInCircle(projection)){
            return true;
        }

        return pointInCircle(p1) || pointInCircle(p2);

    }

    /***
     * Credit : Kevin
     * @param cs
     * @return
     */
    public boolean intersects(CircleShape cs){
        double distance = Math.sqrt(Math.pow(this.x - cs.x,2) + Math.pow(this.y - cs.y,2));

        //Modify both circles normalHit, we don't know which one is the ball
        normalHit[0] = x - cs.x;
        normalHit[1] = y - cs.y;
        /*if(cs.sourceName.equals("ball")){
            cs.normalHit[0] = cs.x - x;
            cs.normalHit[1] = cs.y - y;
        }*/


        return distance <= this.radius + cs.radius;
    }

    public float[] getCenter(){
        return new float[]{x,y};
    }

    public float[] getNormalHit(){
        return normalHit;
    }

}
