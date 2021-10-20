package Game;

import Entities.*;
import Frame.PongEmUp;

import javax.swing.*;

public class GameModel extends Model{
    public GameModel(PongEmUp pongEmUp){
        super(pongEmUp);
        initiateGame();
    }

    private void initiateGame(){

        generateEnemies();
        view = new Playground(this);
        Wall wallRight = new Wall(WIDTH - 10, 0, 10, HEIGHT,this);
        Wall wallLeft = new Wall(0, 0, 10, HEIGHT,this);
        Wall wallUp = new Wall(10, 0, WIDTH-20, 10,this);
        stick = new Stick(WIDTH / 2, HEIGHT - 20,this,50);
        b = new Ball(this);
        addPhysicalObject(b);
        addPhysicalObject(wallRight);
        addPhysicalObject(wallLeft);
        addPhysicalObject(wallUp);
        addPhysicalObject(stick);
        for(Projectile projectile : stick.projectiles)
            addPhysicalObject(projectile);

        for (Entity entity : physicalObjects) {
            if(entity instanceof Stick){
                Stick stick = (Stick) entity;
                for(Projectile projectile : stick.projectiles)
                    addDrawable(projectile);
            }
            addDrawable(entity);
        }

        addDrawable(b);

        setUpBackgroundObjects();
        timer = new Timer(DELAY, this);
        timer.start();
    }
}
