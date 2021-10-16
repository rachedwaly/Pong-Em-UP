package Entities;

import Game.Model;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class StickForAnimation extends Stick{
private Ball ball;


    public StickForAnimation(int x, int y, Model model, int initialWidth, Ball ball) {
        super(x, y, model, initialWidth);
        this.ball=ball;
    }

    @Override
    public void update() {
        if(innerTimer > 80){
            color = Color.BLACK;
        }

        innerTimer += Model.DELAY;
        move();

    }

    public void move(){
        if ((x>=10) && (x<WIDTH/2f)) {
            this.x = max(ball.x,10);
        }
        else{
            this.x=min(ball.x,WIDTH+20-width);
        }
        shape.update(this);

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void drawEntity(Graphics g){
            g.setColor(this.color);
            g.fillRect((int)x,(int)y,width,height);
    }
}
