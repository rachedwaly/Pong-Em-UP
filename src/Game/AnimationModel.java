package Game;

import Entities.*;
import Frame.PongEmUp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AnimationModel extends Model {

    private JPanel mainMenu;



    private int offsetX=30;
    private int offsetY=30; //these offset will help put the animation in place inside the mainmenu

    public AnimationModel(PongEmUp pongEmUp,JPanel panel) {
        super(pongEmUp);
        mainMenu =panel;
        initiateAnimation();
    }


    public void initiateAnimation(){
        Wall wallRight = new Wall(WIDTH - 10+offsetX, offsetY, 10, HEIGHT,this);
        Wall wallLeft = new Wall(+offsetX, offsetY, 10, HEIGHT,this);
        Wall wallUp = new Wall(10+offsetX, offsetY, WIDTH-20, 10,this);
        b = new AnimationBall(250+offsetX, 580,this);
        stick = new StickForAnimation(WIDTH / 2+offsetX, HEIGHT-10+offsetY,this,50,b);
        addPhysicalObject(wallRight);
        addPhysicalObject(wallLeft);
        addPhysicalObject(wallUp);
        addPhysicalObject(stick);
        for (Entity entity : physicalObjects) {
            addDrawable(entity);
        }
        addDrawable(b);
        addPhysicalObject(b);
        timer = new Timer(DELAY, this);
        timer.start();
    }



    @Override
    protected void update(){
        solveCollisions();
        for (int i = 0; i < physicalObjects.size(); i++) {
            physicalObjects.get(i).update();
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
        mainMenu.repaint();
    }

    public int getOffsetX() {
        return offsetX;
    }
    public int getOffsetY() {
        return offsetY;
    }

}
