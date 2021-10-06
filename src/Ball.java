import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.*;


public class Ball extends Entity {
    static int ROLLBACK_FRAMES = 10;
    private float scalarSpeed;
    private ArrayList<Float[]> lastValidPositions = new ArrayList<>();
    private Random r=new Random();

    public Ball(int x, int y) {

        super(x, y);
        this.width = 10;
        this.height = 10;

        scalarSpeed = 1;
        this.speed[0]=1;
        this.speed[1]=1;


    }
    @Override
    public void drawEntity(Graphics g){
        g.setColor(this.color);
        g.fillOval((int)x,(int)y,width,height);
    }

    public void move(){

        this.x+=speed[0];
        this.y+=speed[1];

        if (y>HEIGHT){ //ball passes stick
            x=250;
            y=150;
        }

    }

    public void update(){

        if(lastValidPositions.size() < ROLLBACK_FRAMES){
            lastValidPositions.add(new Float[]{x,y});
        }else{
            lastValidPositions.remove(0);
            lastValidPositions.add(new Float[]{x,y});
        }

        move();
    }

    @Override
    public Rectangle getBounds(){
        return new CircleShape((int)(x + width/2), (int)(y + height/2), width/2);
        //return new Rectangle((int)x, (int)y, width,height);
    }

    @Override
    public void whenCollided(Entity entity) {
        float[] normal = entity.getNormalHit(entity);

        int rollbackIndex = lastValidPositions.size() - 1;
        while(this.getBounds().intersects(entity.getBounds()) && rollbackIndex >= 0){
            x = lastValidPositions.get(rollbackIndex)[0];
            y = lastValidPositions.get(rollbackIndex)[1];
            rollbackIndex--;
        }
        speed = reflectVector(speed,normal);
        speed[0] *= scalarSpeed;
        speed[1] *= scalarSpeed;

    }

    @Override
    public String getEntityTypeName() {
        return "ball";
    }

    public static float dot(float[] a, float[] b){
        return a[0] * b[0] + a[1] * b[1];
    };

    public static float distance(float[] v){
        return (float)Math.sqrt(Math.pow(v[0],2) + Math.pow(v[1],2));
    }
    public static float[] normalize(float[] v){
        float distance = distance(v);
        return new float[]{v[0] / distance,v[1] / distance};
    }

    public static float[] reflectVector(float[] v, float[] normal){
        float[] result = new float[2];
        v = normalize(v);
        normal = normalize(normal);
        result[0] = v[0] - 2 * dot(v,normal) * normal[0];
        result[1] = v[1] - 2 * dot(v,normal) * normal[1];
        return result;
    }

}

class CircleShape extends Rectangle{
    //angle diagonale :
    //meilleur cas : cote/2 + rayon
    //pire cas : diagonale + rayon
    public int radius;
    public CircleShape(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    //Code taken from https://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
    @Override
    public boolean intersects(Rectangle rect){
        Point rCenter = new Point(rect.x + width/2,rect.y + height/2);
        Point circleDistance = new Point();

        circleDistance.x = Math.abs(x - rCenter.x);
        circleDistance.y = Math.abs(y - rCenter.y);

        if (circleDistance.x > (rect.width/2 + radius)) { return false; }
        if (circleDistance.y > (rect.height/2 + radius)) { return false; }

        if (circleDistance.x <= (rect.width/2)) { return true; }
        if (circleDistance.y <= (rect.height/2)) { return true; }

        double cornerDistance = Math.pow((circleDistance.x - rect.width/2),2) +
                Math.pow((circleDistance.y - rect.height/2),2);


        return (cornerDistance <= (Math.pow(radius,2)));

    };

    public boolean intersects(CircleShape cs){
        double distance = Math.sqrt(Math.pow(this.x - cs.x,2) + Math.pow(this.y - cs.y,2));
        return distance <= this.radius + cs.radius;
    }
}



