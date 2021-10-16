package Entities;

import Game.Model;

import java.awt.*;

public class BackGroundObject extends Entity{
    Image photo;
    String name;
    Model model;
    protected float x,y;
    protected float[] speed = new float[2];
    protected int width,height;

    public BackGroundObject(String name, int x, int y, Model model, float[] initialSpeed){
        super(x,y, model);
        this.x=x;
        this.y=y;
        this.name=name;
        this.model = model;
        this.photo= model.getPhoto(name);
        this.width=photo.getWidth(model.getView());
        this.height=photo.getHeight(model.getView());
        this.speed[0]=initialSpeed[0];
        this.speed[1]=initialSpeed[1];
        lookDirection[0]=(this.speed[0]<0)?-1:1;
        lookDirection[1]=0;
    }




    public void update() {
        switch ((speed[0] > 0) ? 1 : -1) {
            case 1 -> {
                if (x + width > WIDTH - 10) {
                    x = -50;
                    if (y + height < HEIGHT / 2) {
                        y = y + Model.random.nextInt(50);
                    } else {
                        y = y - Model.random.nextInt(50);
                    }
                } else {
                    this.x += speed[0];
                }
                break;
            }
            case -1 -> {
                if (x < -10) {
                    x = WIDTH + 50;
                    if (y + height < HEIGHT / 2) {
                        y = y + Model.random.nextInt(50);
                    } else {
                        y = y - Model.random.nextInt(50);
                    }
                } else {
                    this.x += speed[0];
                }
                break;
            }
        }
        shape.update(this);
    }


    @Override
    public void whenCollided(Entity entity) {
    }

    @Override
    public String getEntityTypeName() {
        return name;
    }

    public void drawEntity(Graphics g) {
        g.drawImage(photo,(int)x,(int)y, model.getView());
    }



}
