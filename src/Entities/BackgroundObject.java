package Entities;

import AltLib.ImageLoader;
import Game.Handler;

import java.awt.*;

public class BackgroundObject extends Entity{
    /**
     * photo associated to the bg object
     */
    Image photo;
    String name;
    Handler handler;
    protected float x,y;
    protected float[] speed = new float[2];
    protected int width,height;

    /**
     * Credits: Rached
     * @param name name of the backgroud object
     * @param x initial x position of the background object
     * @param y initial y position of the background object
     * @param handler reference to the handler
     * @param initialSpeed
     */
    public BackgroundObject(String name, int x, int y, Handler handler, float[] initialSpeed){
        super(x,y, handler);
        this.x=x;
        this.y=y;
        this.name=name;
        this.handler = handler;
        if(name.equals("plane"))
            this.photo= ImageLoader.planeImage;
        if(name.equals("cloud"))
            this.photo= ImageLoader.cloudImage;
        this.width=photo.getWidth(handler.getView());
        this.height=photo.getHeight(handler.getView());
        this.speed[0]=initialSpeed[0];
        this.speed[1]=initialSpeed[1];
        lookDirection[0]=(this.speed[0]<0)?-1:1;
        lookDirection[1]=0;
    }


    /**
     * Cyclic moving for each background object
     */
    public void update() {
        //the update has two behaviours depending on the sign of the initial speed
        switch ((speed[0] > 0) ? 1 : -1) {
            case 1 -> {
                if (x + width > WIDTH - 10) {
                    x = -50;
                    if (y + height < HEIGHT / 2) {
                        y = y + Handler.random.nextInt(50);
                    } else {
                        y = y - Handler.random.nextInt(50);
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
                        y = y + Handler.random.nextInt(50);
                    } else {
                        y = y - Handler.random.nextInt(50);
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
        g.drawImage(photo,(int)x,(int)y, handler.getView());
    }



}
