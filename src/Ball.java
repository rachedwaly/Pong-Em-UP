import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Ball extends Entity {
    static int ROLLBACK_FRAMES = 10;
    private float scalarSpeed;
    private ArrayList<Float[]> lastValidPositions = new ArrayList<>();

    private Random r=new Random();

    public Ball(int x, int y) {

        super(x, y);
        this.width = 10;
        this.height = 10;
        name = "ball";

        scalarSpeed = 2;
        this.speed[0]=2;
        this.speed[1]=2;


    }
    @Override
    public void drawEntity(Graphics g){
        g.setColor(this.color);
        g.fillOval((int)x,(int)y,width,height);
        g.setColor(Color.WHITE);
        //g.drawString( Integer.toString((int)(x + width/2) + (int)(y + height/2)),(int)x,(int)y );
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
    public CircleShape getBounds(){
        return new CircleShape((int)x + width/2, (int)y + height/2,width/2);
    }

    @Override
    public void whenCollided(Entity entity) {
        scalarSpeed = Math.max(2,scalarSpeed * 3/4);
        float[] normal = entity.getNormalHit(entity);

        speed = CustomShape.reflectVector(speed,normal);
        //influence trajectory
        speed[0] = speed[0] + entity.speed[0];
        speed[1] = speed[1] + entity.speed[1];

        speed = CustomShape.normalize(speed);

        speed[0] = speed[0] * scalarSpeed;
        speed[1] = speed[1] * scalarSpeed;

        while(this.getBounds().intersects(entity.getBounds())){
            move();
            scalarSpeed = Math.max(scalarSpeed,CustomShape.distance(entity.speed));
        }

    }

    @Override
    public String getEntityTypeName() {
        return "ball";
    }



}





