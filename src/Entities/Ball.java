package Entities;
import Game.Model;
import shape.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Ball extends Entity {
    private float scalarSpeed;

    private Random r=new Random();

    public Ball(int x, int y, Model model) {

        super(x, y,model);
        this.width = 10;
        this.height = 10;
        name = "ball";

        scalarSpeed = 1;
        this.speed[0]=2;
        this.speed[1]=2;

        shape = new CircleShape(x + width/2,y + height/2,width/2);
    }

    @Override
    public void drawEntity(Graphics g){
        g.setColor(this.color);
        g.fillOval((int)x,(int)y,width,height);
        g.setColor(Color.WHITE);
    }

    public void move(){
        scalarSpeed = Math.max(2,scalarSpeed - 0.01f);
        this.x += speed[0] * scalarSpeed;
        this.y += speed[1] * scalarSpeed;

        if (y>HEIGHT || y < 0){ //ball passes stick or glitches out
            x=250;
            y=150;
        }
        shape.update(this);
    }

    public void update(){

            //scalarSpeed = Math.max(2,scalarSpeed - 1f / 1000 * Model.DELAY); //loses 1 speed every 8 seconds
        innerTimer += Model.DELAY;
        move();
    }

    @Override
    public void whenCollided(Entity entity) {
        /*switch(entity.getEntityTypeName()){
            case "stick":
            case "wall":
                break;
            case "enemy":
            case "bonus":
            case "projectile": //hitting something other than wall maintains speed for 8 seconds
                innerTimer = 81;
                break;
        }*/
        float[] normal = ((CircleShape)shape).getNormalHit();
        if(normal[0] == 0 && normal[1] == 0)
            System.out.println("bad normal");
        speed = CustomShape.reflectVector(speed,normal); //is normalized

        //influence trajectory
        float[] normSpeed = CustomShape.normalize(entity.speed);
        if(CustomShape.dot(speed,normSpeed) >= 0){
            speed[0] = speed[0] + normSpeed[0]/2;
            speed[1] = speed[1] + normSpeed[1];

        }

        speed = CustomShape.normalize(speed);

        while(this.getShape().intersects(entity.getShape())){
            scalarSpeed = Math.max(scalarSpeed,CustomShape.distance(entity.speed));
            update();
        }

    }

    @Override
    public String getEntityTypeName() {
        return "ball";
    }



}





