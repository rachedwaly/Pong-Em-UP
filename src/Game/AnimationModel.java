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

    private JPanel test;



    private int offsetX=30;
    private int offsetY=30; //these offset will help put the animation in place inside the mainmenu

    public AnimationModel(PongEmUp pongEmUp,JPanel panel) throws IOException {
        super(pongEmUp);
        test=panel;
        initiateAnimation();
    }


    public void initiateAnimation() throws IOException {
        loadPhotos();
        VerticalWall wallRight = new VerticalWall(WIDTH - 10+offsetX, offsetY, 10, HEIGHT,this);
        VerticalWall wallLeft = new VerticalWall(+offsetX, offsetY, 10, HEIGHT,this);
        HorizontalWall wallUp = new HorizontalWall(10+offsetX, offsetY, WIDTH-20, 10,this);
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

    public void loadPhotos() throws IOException {
        BufferedImage ph1= ImageIO.read(new File("Resources/background lvl1.jpg"));
        allImages.put("lvl1",(Image)ph1);
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
        test.repaint();
    }

    public int getOffsetX() {
        return offsetX;
    }
    public int getOffsetY() {
        return offsetY;
    }

}
