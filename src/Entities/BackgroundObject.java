package Entities;

import Game.Model;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BackgroundObject extends Entity{
    Image photo;
    String name;
    Model model;
    public BackgroundObject(String name, int x, int y, Model model){
        this.x=x;
        this.y=y;
        this.name=name;
        this.model=model;
        this.photo=model.getPhoto(name);
        this.width=photo.getWidth(model.getView());
        this.height=photo.getHeight(model.getView());
        this.speed[0]=1;
        this.speed[1]=0;
    }
    @Override
    public void update() {
        if (this.x>=WIDTH-10){
            this.x=0;
        }
        else{
            this.x+=speed[0];
        }
    }
    @Override
    public void drawEntity(Graphics g) {
        g.drawImage(photo,(int)x,(int)y,model.getView());
    }

    @Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        return null;
    }
}
