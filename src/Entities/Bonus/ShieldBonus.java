package Entities.Bonus;

import AltLib.ImageLoader;
import Entities.Stick;
import Game.Handler;

import java.awt.*;

public class ShieldBonus extends Bonus{

    public ShieldBonus(String name, float x, float y, int delay, Stick stick, Handler handler) {
        super(name, x, y, delay, stick, handler);
    }

    @Override
    public void drawEntity(Graphics g) {
        if (!acquired) {
            g.setColor(color);
            g.drawImage(ImageLoader.shieldBonusImage, (int) x, (int) y, width, height, handler.getView());
        }
        else{
            int x1=(int)stick.getX();
            int y1=(int)stick.getY();
            int w1=stick.getWidth();
            g.drawImage(ImageLoader.shieldStickImage,(int)x1+w1+5,(int)y1,20,
                    15,
                    handler.getView());
        }
    }


    @Override
    public void applyBonus(){
        stick.setShieldOn(true);
    }

    @Override
    public void removeBonus(){
        stick.setShieldOn(false);

    }

    @Override
    public void drawInStatusBar(Graphics g,int x,int y) {
        g.drawImage(ImageLoader.shieldBonusImage,x,y,width,height,null);
    }

}
