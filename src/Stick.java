import static java.lang.Math.max;
import static java.lang.Math.min;

public class Stick extends Entity{

    protected int dx;


    public Stick(int x, int y){
        super(x,y);
        this.width=40;
        this.height=10;
        orientation=true;

    }
    public void move(){

        if ((x>=10) && (x<WIDTH/2)) {
            //y<=HEIGHT-10-height
            this.x = max(this.x+ dx,10);
        }
        else{
            this.x=min(this.x+ dx,WIDTH-10-width);
        }

    }





}
