import java.util.ArrayList;
import java.util.Random;


import static java.lang.Math.min;


public class Ball extends Entity {
    private int slope;
    private boolean direction;
    private int speed;
    public Ball(int x, int y) {

        super(x, y);
        this.width=10;
        this.height=10;

        this.speed=1;
        Random r=new Random();
        slope= r.nextInt(4)+1;
        direction=r.nextBoolean();
        //slope=5;
        //direction=true;



    }
    public void move(int speed,boolean dir,int slope){
        this.speed=speed;
        this.direction=dir;
        this.slope=slope;

        this.y=slope*speed+this.y;
        this.x=x+speed*(direction ? 1:-1);

        if ((x>500) || (x<0)){
            x=250;
            y=150;
        }
    }

    public void move(){
        this.y=slope*speed+this.y;
        this.x=x+speed*(direction ? 1:-1);

        if ((x>500) || (x<0)){
            x=250;
            y=150;
        }
    }

    public void solveCollisions(ArrayList<Entity> list){
        for (Entity entity : list) {


            if (this.getBounds().intersects(entity.getBounds())) {
                slope = -slope;
                if (entity.isOrientation()) {
                    if (entity.speed == 0) {
                        slope = -slope;
                        direction = !direction;
                    } else {
                        slope = -min(-slope + entity.speed, 3);
                        direction = !direction;
                    }
                }
                break;
            }

        }

    }



}
