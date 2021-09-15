import static java.lang.Math.max;
import static java.lang.Math.min;

public class Stick extends Entity{




    public Stick(int x, int y){
        super(x,y);
        this.width=10;
        this.height=30;
        orientation=true;

    }
    public void move(){

        if ((y>=10) && (y<=HEIGHT-10-height)) {
            this.y = max(this.y+dy,10);
        }
        else{
            this.y=min(this.y+dy,HEIGHT-10-height);
        }

    }




    protected int dy;
}
