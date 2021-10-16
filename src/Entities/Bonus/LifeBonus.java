package Entities.Bonus;

import AltLib.ImageLoader;
import Entities.Stick;
import Game.Model;

import java.awt.*;

public class LifeBonus extends Bonus{
    public LifeBonus(String name, float x, float y, int delay, Stick stick, Model model) {
        super(name, x, y, delay, stick, model);
    }



    @Override
    public void update() {
        if (!acquired) move();
        else{
                model.removeBonus(this);
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
    public void drawEntity(Graphics g) {
        if (!acquired) {
            g.setColor(color);
            g.drawImage(ImageLoader.healthImage, (int) x, (int) y, width/2, height/2,
                    model.getView());
        }
    }
}
