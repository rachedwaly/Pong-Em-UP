package Entities.Bonus;

import AltLib.ImageLoader;
import Entities.Stick;
import Game.Handler;

import java.awt.*;

public class LifeBonus extends Bonus{
    public LifeBonus(String name, float x, float y, int delay, Stick stick, Handler handler) {
        super(name, x, y, delay, stick, handler);
    }



    @Override
    public void update() {
        if (!acquired) move();
        else{
                handler.removeBonus(this);
            }
        }


    @Override
    protected void applyBonus() {
        stick.setLives(stick.getLives()+1);
    }

    @Override
    protected void removeBonus() {

    }

    @Override
    public void drawInStatusBar(Graphics g,int x, int y) {


    }

    @Override
    public void drawEntity(Graphics g) {
        if (!acquired) {
            g.setColor(color);
            g.drawImage(ImageLoader.healthImage, (int) x, (int) y, width/2, height/2,
                    handler.getView());
        }
    }
}
