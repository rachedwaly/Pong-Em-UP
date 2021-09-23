import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.*;


public class Ball extends Entity {
    private int slope;
    private boolean direction;

    public Ball(int x, int y) {

        super(x, y);
        this.width=10;
        this.height=10;

        this.speedy=1;

        Random r=new Random();
        slope= r.nextInt(2)+1;
        speedx=slope*slope;
        direction=r.nextBoolean();
        //slope=5;
        //direction=true;



    }


    public void move(){
        speedx=slope*speedy;
        this.x=speedx+this.x;
        this.y=y+speedy;

        if (y>HEIGHT){
            x=250;
            y=150;
        }
    }




    public void solveCollisions(ArrayList<Entity> list){
        for (Entity entity : list) {
            if (this.getBounds().intersects(entity.getBounds())) {
                if (!entity.isOrientation()){
                    slope = -slope;
                }
                else{
                    //taking in consideration the speed of the stick
                    int sgn1= slope/abs(slope);
                    int sgn2=speedy/abs(speedy);
                    if (entity.speedx == 0)  {

                        slope=-slope;
                        speedy=-speedy;

                    } else {
                        if (speedx*entity.speedx<0){
                            System.out.println("zall1");
                            slope=-slope/2;
                            speedy=-sgn2;
                            if (slope==0){
                                slope=-sgn1;
                            }
                        }
                        else if(speedx*entity.speedx>0) {
                            System.out.println("zall2");
                            slope=-sgn1*min(abs(slope)*2,3);
                            speedy=-sgn2*2;
                        }
                    }

                    }
                break;
                }

            }

        }

    }




