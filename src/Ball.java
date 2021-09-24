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

        slope= r.nextInt(2)+1;


    }
    @Override
    public void drawEntity(Graphics g){
        g.fillOval(x,y,width,height);
    }

    public void move(){

        speed[0]=slope*speed[1];
        this.x+=speed[0];
        this.y+=speed[1];
        speed[1]=min(speed[1],2);
        if (y>HEIGHT){
            x=250;
            y=150;
        }

    }
/*
    public void solveCollisions(ArrayList<Entity> list){
        for (Entity entity : list) {

            if (this.getBounds().intersects(entity.getBounds())) {//checking with which object the ball collide

                for(PhysicalBoundarie side : entity.getPhysicalBoundaries()){
                    //looping on the sides of the object to determine which side we are colliding with

                    if (this.getBounds().intersects(side)) {


                        if (!side.isOrientation()) {
                            slope = -slope;
                        } else {
                            //taking in consideration the speed of the entity that collided with the ball
                            int sgn1 = slope / abs(slope);
                            int sgn2 = speed[1] / abs(speed[1]);

                            if (entity.speed[0] == 0) {
                                slope = -slope;
                                speed[1] = -speed[1];
                            } else {
                                if (speed[0] * entity.speed[0] < 0) {
                                    slope = -slope / 2;
                                    speed[1] = -sgn2;
                                    if (slope == 0) {
                                        slope = -sgn1;
                                    }
                                } else if (speed[0] * entity.speed[0] > 0) {
                                    slope = -sgn1 * min(abs(slope) * 2, 3);
                                    speed[1] = -sgn2 * 2;
                                }
                            }
                        }
                    }
                    }
                break;
            }

        }

    }

*/
    public void solveCollisions(ArrayList<Entity> list){
        int flag=0;
        for (Entity entity : list) {

            if (this.getBounds().intersects(entity.getBounds())) {//checking with which object the ball collide

                for(PhysicalBoundarie side : entity.getPhysicalBoundaries()){
                    //looping on the sides of the object to determine which side we are colliding with

                    if (this.getBounds().intersects(side)) {

                        if (!side.isOrientation()) {
                            slope = -slope;
                        } else {
                            //taking in consideration the speed of the entity that collided with the ball
                            int sgn1 = slope / abs(slope);
                            int sgn2 = speed[1] / abs(speed[1]);
                            if ((entity.speed[0]==0) && (entity.speed[1]!=0)){
                                    System.out.println("different direction");
                                    slope=-slope;
                                    speed[1]=-entity.speed[1];

                            }
                            else if ((entity.speed[0]!=0) && (entity.speed[1]==0)){

                                if ((entity.speed[0]*speed[0])<0){

                                    slope = slope / 2;
                                    speed[1] = -sgn2;
                                    if (slope == 0) {
                                        slope = -sgn1;
                                    }
                                }
                                else{

                                    slope = -sgn1 * min(abs(slope) * 2, 3);
                                    speed[1] = -sgn2 * 2;
                                }
                            }
                            else if ((entity.speed[0]!=0) && (entity.speed[1]!=0)){
                                if ((entity.speed[0]*speed[0]<0) && (entity.speed[0]*speed[0]<0)){
                                    slope=-slope;
                                    speed[1] = -sgn2 * 2;
                                }
                                else if ((entity.speed[0]*speed[0]>0) && (entity.speed[0]*speed[0]<0)){
                                    speed[1] = -sgn2 * 2;
                                    slope=-r.nextInt(2)+1;
                                }
                                else if ((entity.speed[0]*speed[0]<0) && (entity.speed[0]*speed[0]>0)){
                                    speed[1] = -sgn2;
                                    slope=-r.nextInt(2)+1;
                                }
                                else if ((entity.speed[0]*speed[0]>0) && (entity.speed[0]*speed[0]>0)){
                                    slope=-slope;
                                    speed[1]=-speed[1];
                                }
                            }
                            else if ((entity.speed[0]==0) && (entity.speed[1]==0)){
                                slope=-slope;
                                speed[1]=-speed[1];
                            }
                        }
                    }
                }
                break;
            }

        }

    }

    }




