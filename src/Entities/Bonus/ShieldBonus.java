package Entities.Bonus;

import AltLib.ImageLoader;
import Entities.Stick;
import Game.Model;

import java.awt.*;

public class ShieldBonus extends Bonus{

    public ShieldBonus(String name, float x, float y, int delay, Stick stick, Model model) {
        super(name, x, y, delay, stick, model);
    }

    @Override
    public void drawEntity(Graphics g) {
        if (!acquired) {
            g.setColor(color);
            g.drawImage(ImageLoader.shieldBonusImage, (int) x, (int) y, width, height, model.getView());
        }
        else{
            int x1=(int)stick.getX();
            int y1=(int)stick.getY();
            int w1=stick.getWidth();
            g.drawImage(ImageLoader.shieldStickImage,(int)x1+w1+5,(int)y1,20,
                    15,
                    model.getView());
        }
    }


    @Override
    public void applyBonus(){
        stick.setShieldOn(true);
        System.out.println("shield");
    }

    @Override
    public void removeBonus(){
        stick.setShieldOn(false);

    }

}
