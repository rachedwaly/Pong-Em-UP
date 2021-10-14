package Entities;
import shape.RectangleShape;
import java.awt.*;
import Game.*;

public class Wall extends Entity{

    public Wall(int x,int y,int w,int h,Model model){
        super(x,y,w,h,model);
    }

    @Override
    public void update() {
        if(innerTimer > 80)
            color = Color.BLACK;
        innerTimer += Model.DELAY;
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
