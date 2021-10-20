package Entities;
import java.awt.*;
import Game.*;

/***
 * Credit : Rached
 */
public class Wall extends Entity{

    public Wall(int x, int y, int w, int h, Handler handler){
        super(x,y,w,h, handler);
    }

    @Override
    public void update() {
        if(innerTimer > 80)
            color = Color.BLACK;
        innerTimer += Handler.DELAY;
    }

    @Override
    public void whenCollided(Entity entity) {
        color = Color.YELLOW;
        innerTimer = 0;
    }

    @Override
    public String getEntityTypeName() {
        return "wall";
    }

    @Override
    public void drawEntity(Graphics g){
        g.setColor(this.color);
        g.fillRect((int)x,(int)y,width,height);

    }

}
