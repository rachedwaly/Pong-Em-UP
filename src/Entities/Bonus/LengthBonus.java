package Entities.Bonus;

import Entities.Stick;
import Game.Model;

import java.awt.*;

public class LengthBonus extends Bonus{
    private int lengthToAdd;

    private int powerOftheLength; //this will determine how much we should add health to the stick

    public LengthBonus(String name, float x, float y, int delay, Stick stick,
                       int lengthToAdd, Model model) {
        super(name, x, y, delay, stick, model);
        this.lengthToAdd=lengthToAdd;
        this.powerOftheLength=
                (int) Math.ceil((float)(lengthToAdd+stick.getWidth())/(float)stick.getWidth());



    }

    @Override
    protected void applyBonus() {
        stick.setWidth(stick.getWidth()+lengthToAdd);
        stick.setInitialWidth(stick.getInitialWidth()+lengthToAdd);
        stick.setMaxHealth(stick.getMaxHealth()*powerOftheLength);
        stick.setHealth(stick.getHealth()*powerOftheLength);
        stick.getShape().update(stick);
    }

    @Override
    protected void removeBonus() {
        stick.setInitialWidth(stick.getInitialWidth()-lengthToAdd);
        stick.setMaxHealth(stick.getMaxHealth()/powerOftheLength);
        if ((stick.getHealth())>(stick.getMaxHealth())) {
            stick.setHealth(stick.getMaxHealth());
        }
        if ((stick.getWidth())>(stick.getInitialWidth())){
            stick.setWidth(stick.getInitialWidth());
        }
        stick.getShape().update(stick);
    }

    @Override
    public void drawEntity(Graphics g) {
        if (!acquired) {
            g.setColor(color);
            g.drawImage(model.getPhoto("muscle"), (int) x, (int) y, width, height,
                    model.getView());
        }
    }
}
