package shape;

import Entities.*;

public class CircleShape extends CustomShape{
    //here x,y is center of the circle
    public int radius;
    public CircleShape(int x, int y, int radius){
        super(x,y);
        this.radius = radius;
    }

    @Override
    public void update(Entity source) {
        radius = source.getWidth()/2;
        x = (int)source.getX()/2 + radius;
        y = (int)source.getY()/2 + radius;

    }

    //Code taken from https://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
    @Override
    public boolean intersects(CustomRectangle rect){
        if(rect.x <= x && x <= rect.x + rect.width &&
            rect.y <= y && y <= rect.y + rect.height)
            return true;

        else{
            //can do minDistance(p(i),p(i + 1), center) for all polygons
            float[] topleft = new float[]{rect.x, rect.y};
            float[] topright = new float[]{rect.x + rect.width, rect.y};
            float[] botleft = new float[]{rect.x, rect.y + rect.height};
            float[] botright = new float[]{rect.x + rect.width, rect.y + rect.height};
            return  segmentInCircle(topleft,topright) ||
                    segmentInCircle(topright,botright)||
                    segmentInCircle(botright,botleft)||
                    segmentInCircle(botleft,topleft);
        }

    }

    public boolean pointInCircle(float[] p){
        return Math.sqrt(distance2(p, new float[]{x,y})) <= radius;
    }

    /**
     *
     * @param p1
     * @param p2
     * @return
     */
    public boolean segmentInCircle(float[] p1, float[] p2){
        float[] center = new float[]{x,y};
        float[] projection;

        if(Math.abs(p2[0] - p1[0])  <= 0.01 && Math.abs(p2[1] - p1[1])  <= 0.01){ //p1 == p2
            return pointInCircle(p1);
        }
        if(Math.abs(p2[0] - p1[0])  <= 0.01){ //div by 0 exception
            projection = new float[]{p1[0],center[1]};
        }else{
            float m = (p2[1] - p1[1]) / (p2[0] - p1[0]);
            float b = p1[1] - (m * p1[0]);

            projection = new float[]{   (m * center[1] + center[0] - m * b) / (m * m + 1),
                    (m * m * center[1] + m * center[0] + b) / (m * m + 1) };
        }


        if(Math.min(p1[0],p2[0]) <= projection[0] && projection[0] <= Math.max(p1[0],p2[0]) &&
           Math.min(p1[1],p2[1]) <= projection[1] && projection[1] <= Math.max(p1[1],p2[1]) &&
                pointInCircle(projection)){

            return true;
        }
        return pointInCircle(p1) || pointInCircle(p2);

    }

    public boolean intersects(CircleShape cs){
        double distance = Math.sqrt(Math.pow(this.x - cs.x,2) + Math.pow(this.y - cs.y,2));
        return distance <= this.radius + cs.radius;
    }


}
