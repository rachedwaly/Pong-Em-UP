import java.awt.*;

public class Wall extends Entity{
    public Wall(int x,int y,int w,int h){
        super(x,y,w,h);

    }

    @Override
    public void drawEntity(Graphics g){
        g.drawRect(x,y,width,height);

    }
}
