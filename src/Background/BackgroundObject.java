package Background;

import Entities.Entity;
import Game.Model;
import shape.CustomShape;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BackgroundObject {
    Image photo;
    String name;
    Model model;
    protected float x,y;
    protected float[] speed = new float[2];
    protected int width,height;

    public BackgroundObject(String name, int x, int y, Model model){
        this.x=x;
        this.y=y;
        this.name=name;
        this.model=model;
        this.photo=model.getPhoto(name);
        this.width=photo.getWidth(model.getView());
        this.height=photo.getHeight(model.getView());
        this.speed[0]=0.5f;
        this.speed[1]=0;
    }

    public void update() {
        if (this.x>=Entity.WIDTH-10){
            this.x=0;
        }
        else{
            this.x+=speed[0];
        }
    }

    public void drawEntity(Graphics g) {
        g.drawImage(photo,(int)x,(int)y,model.getView());
    }


}
