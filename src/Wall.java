import java.awt.*;
import java.util.ArrayList;

public class Wall extends Entity{
    public Wall(int x,int y,int w,int h){
        super(x,y,w,h);

    }

    @Override
    public void drawEntity(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);

    }




}
