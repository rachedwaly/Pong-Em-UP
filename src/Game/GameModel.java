package Game;

import Entities.*;
import Frame.PongEmUp;

import javax.swing.*;
import java.io.IOException;

public class GameModel extends Model{
    public GameModel(PongEmUp pongEmUp){
        super(pongEmUp);
        initiateGame();
    }

    private void initiateGame(){
        generateEnemies();
        view = new PlayGround(this);
        Wall wallRight = new Wall(WIDTH - 10, 0, 10, HEIGHT,this);
        Wall wallLeft = new Wall(0, 0, 10, HEIGHT,this);
        Wall wallUp = new Wall(10, 0, WIDTH-20, 10,this);
        stick = new Stick(WIDTH / 2, HEIGHT - 20,this,50);
        b = new Ball(250, 580,this);
        addPhysicalObject(wallRight);
        addPhysicalObject(wallLeft);
        addPhysicalObject(wallUp);
        addPhysicalObject(stick);
        for(Projectile projectile : stick.projectiles)
            addPhysicalObject(projectile);

        if(!DEBUGMODE){
            for(Enemy enemy : ennemies){
                addPhysicalObject(enemy);
                for(Projectile projectile : enemy.projectiles)
                    addPhysicalObject(projectile);
            }
        }

        for (Entity entity : physicalObjects) {
            if(entity instanceof Enemy){
                Enemy enemy = (Enemy) entity;
                for(Projectile projectile : enemy.projectiles)
                    addDrawable(projectile);
            }
            else if(entity instanceof Stick){
                Stick stick = (Stick) entity;
                for(Projectile projectile : stick.projectiles)
                    addDrawable(projectile);
            }
            addDrawable(entity);
        }
        addDrawable(b);
        addPhysicalObject(b);
        setUpBackgroundObjects();
        timer = new Timer(DELAY, this);
        timer.start();
    }
}
