import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.*;


public class Ball extends Entity {
    private int slope;
    private float[] directionVector=new float[2];
    private Random r=new Random();

    public Ball(int x, int y) {

        super(x, y);
        this.width=10;

        this.height=width;
        this.speed[1]=1;
        this.speed[0]=0;

        slope= r.nextInt(1)+1;


    }
    @Override
    public void drawEntity(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval((int)x,(int)y,width,height);
    }

    @Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        return null;
    }

    public void move(){

        speed[0]=slope*speed[1];
        this.x+=speed[0];
        this.y+=speed[1];

        if (y>HEIGHT){
            x=250;
            y=150;
        }

    }

    public void update(){
        move();
    }

    public void solveCollisions(ArrayList<Entity> list) {

        for (Entity entity : list) {
            if (this.getBounds().intersects(entity.getBounds())) {//checking with which object the ball collide

                for (PhysicalBoundarie side : entity.getPhysicalBoundaries()) {
                    //looping on the sides of the object to determine which side we are colliding with

                    if (this.getBounds().intersects(side)) {

                        if (!side.isOrientation()) {
                            slope = -slope;
                        } else {
                            //taking in consideration the speed of the entity that collided with
                            // the ball
                            //taking in consideration the speed along the y axis
                            if ((entity.speed[1]==0)){
                                speed[1]=-speed[1];
                                //shifting the ball a little more to avoid more collisions
                                y=y+2*speed[1];

                                //taking in consideration the speed along the x axis
                                updateSlope(entity);

                            }

                            else if (entity.speed[1]*speed[1]<0){

                                int sgn2 = (int)(speed[1] / abs(speed[1]));
                                speed[1]=-sgn2*(min(abs(speed[1])+1,2));
                                //shifting the ball a little more to avoid more collisions
                                y=y+2*speed[1];
                                //taking in consideration the speed along the x axis
                                updateSlope(entity);
                            }

                            else {
                                int sgn2 = (int)(speed[1] / abs(speed[1]));

                                speed[1]=-sgn2*(max(abs(speed[1])-1,1));
                                //shifting the ball a little more to avoid more collisions
                                y=y+2*speed[1];
                                //taking in consideration the speed along the x axis
                                updateSlope(entity);
                            }


                        }

                    }
                }
                break;
            }

        }
    }


    void updateSlope(Entity entity) {
        int sgn1 = slope / abs(slope);
        if (entity.speed[0]==0){
            slope=-slope;
        }
        else if ((entity.speed[0]*speed[0]<0)){
            slope=sgn1*(max(abs(slope)-1,1));
        }
        else{
            slope=-sgn1*(min(abs(slope)+1,2));

        }
    }
}



