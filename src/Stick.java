import static java.lang.Math.max;
import static java.lang.Math.min;

public class Stick extends Entity{

    protected int dx,dy;


    public Stick(int x, int y){
        super(x,y);
        this.width=40;
        this.height=10;
        orientation=true;

    }
    public void move(){

        if ((x>=10) && (x<WIDTH/2)) {
            this.x = max(this.x+ dx,10);
        }
        else{
            this.x=min(this.x+ dx,WIDTH-10-width);
        }

        if ((y<=HEIGHT-20) && (y>HEIGHT*3/4)){
            this.y=min(this.y+dy,HEIGHT-30);
        }
        else{
            this.y=max(this.y+dy,HEIGHT*3/4);
        }


    }





}
