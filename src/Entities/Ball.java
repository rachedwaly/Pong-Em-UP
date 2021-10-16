package Entities;
import Game.Model;
import shape.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Ball extends Entity {
    private float scalarSpeed;
    public static final int BALL_DAMAGE = 5;
    private Random r=new Random();

    public Ball(int x, int y, Model model) {

        super(x, y,model);
        this.width = 10;
        this.height = 10;
        name = "ball";

        scalarSpeed = 2;
        this.speed[0]=scalarSpeed;
        this.speed[1]=scalarSpeed;

        shape = new CircleShape(x + width/2,y + height/2,width/2);
    }

    @Override
    public void drawEntity(Graphics g){
        g.setColor(this.color);
        g.fillOval((int)x,(int)y,width,height);
        g.setColor(Color.WHITE);
    }

    public void move(){

        this.x += speed[0] * scalarSpeed;
        this.y += speed[1] * scalarSpeed;

        if (y>HEIGHT || y < 0 ){ //ball passes stick or glitches out
            reset();
        }

    }

    public void update(){

        if(innerTimer > 8080)
            scalarSpeed = Math.max(2,scalarSpeed - 1f / 750 * Model.DELAY); //loses 1 speed every 6 seconds
        innerTimer += Model.DELAY;
        move();
        shape.update(this);
    }

    @Override
    public void whenCollided(Entity entity) {
        switch(entity.getEntityTypeName()){
            case "stick":
            case "wall":
                break;
            case "enemy":
            case "bonus":
            case "stickprojectile": //hitting something other than wall maintains speed for 8 seconds
            case "enemyprojectile": //hitting something other than wall maintains speed for 8 seconds
                innerTimer = 81;
                break;
        }

        float[] normal = CustomShape.normalize( ((CircleShape)shape).getNormalHit() );
        if(normal[0] == 0 && normal[1] == 0)
            System.out.println("bad normal");


        debugLog();
        //influence trajectory
        float[] normSpeed = CustomShape.normalize(entity.speed);
        if(CustomShape.dot(speed,normal) > 0){
            speed[0] = speed[0] + normSpeed[0]/2;
            speed[1] = speed[1] + normSpeed[1];
            if(entity instanceof Stick)
                System.out.println("more speed");
        }else{
            if(entity instanceof Stick)
                System.out.println("reflect");
            speed = CustomShape.reflectVector(speed,normal); //is normalized
        }
        if(entity instanceof Stick)
            System.out.println("normal : (" + normal[0] + ", " + normal[1] + ")");
        speed = CustomShape.normalize(speed);

        int stuckCounter = 0;
        while(this.getShape().intersects(entity.getShape()) && stuckCounter < 10){
            scalarSpeed = Math.max(scalarSpeed,CustomShape.distance(entity.speed));
            update();
            //stuckCounter++;
        }
        if(stuckCounter == 10)
            reset();

    }

    @Override
    public String getEntityTypeName() {
        return "ball";
    }

    public void reset(){
        //TODO : explosion animation + time wait + lose life
        x=250;
        y=150;

        scalarSpeed = 2;
        this.speed[0]=scalarSpeed;
        this.speed[1]=scalarSpeed;
    }

}





