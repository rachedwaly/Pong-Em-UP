import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Model implements ActionListener, KeyListener {

    public static Random random  = new Random();
    public static int HEIGHT=600,WIDTH=300;
    static final int DELAY = 8;


    private Entity entityBuffer1; // prevent excessive memory usage for collisions
    private Entity entityBuffer2;
    public Stick s1;
    public Ball b;
    private HashMap<String,Image> allImages=new HashMap<>();
    private PlayGround view;
    public Timer timer;

    public ArrayList<Entity> physicalObjects = new ArrayList<>();
    private Enemy[] level1List = {
                                    new Enemy(Enemy.SENTRY,450,-50,450,200),
                                    new Enemy(Enemy.SENTRY,150,-50,300,200),
                                    new Enemy(Enemy.SENTRY,150,-50,500,100),
                                    new Enemy(Enemy.SENTRY,250,-50,400,150),
                                    new Enemy(Enemy.SENTRY,150,-50,250,350),
                                    new Enemy(Enemy.SENTRY,400,-50,550,300),

                                };

    public Model() throws IOException {
        random = new Random();
        loadPhotos();
        view = new PlayGround(this);
        VerticalWall wallRight = new VerticalWall(WIDTH - 10, 0, 10, HEIGHT);
        VerticalWall wallLeft = new VerticalWall(0, 0, 10, HEIGHT);
        HorizontalWall wallUp = new HorizontalWall(10, 0, WIDTH-20, 10);
        s1 = new Stick(WIDTH / 2, HEIGHT - 20);
        b = new Ball(10, 250);

        wallRight.name = "wallRight";
        addPhysicalObject(b);
        addPhysicalObject(wallRight);
        addPhysicalObject(wallLeft);
        addPhysicalObject(wallUp);
        addPhysicalObject(s1);

        for(Projectile projectile : s1.projectiles)
            addPhysicalObject(projectile);

        for(Enemy enemy : level1List){
            addPhysicalObject(enemy);
            for(Projectile projectile : enemy.projectiles)
                addPhysicalObject(projectile);
        }

        for (Entity entity : physicalObjects) {
            if(entity instanceof Enemy){
                Enemy enemy = (Enemy) entity;
                for(Projectile projectile : enemy.projectiles)
                    view.addDrawable(projectile);
            }
            else if(entity instanceof Stick){
                Stick stick = (Stick) entity;
                for(Projectile projectile : stick.projectiles)
                    view.addDrawable(projectile);
            }
            view.addDrawable(entity);
        }
        view.addDrawable(b); //to remove

        timer = new Timer(DELAY, this);

        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
        view.update();
    }

    public void addPhysicalObject(Entity e){
        physicalObjects.add(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    s1.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
    s1.keyReleased(e);
    }
    private void update(){
        solveCollisions();
        for(Entity e : physicalObjects){
            e.update();
        }
    }

    public void solveCollisions(){
        for(int i = 0; i < physicalObjects.size() - 1; i++){
            entityBuffer1 = physicalObjects.get(i);
            for(int j = i + 1; j < physicalObjects.size(); j++){
                entityBuffer2 = physicalObjects.get(j);
                if(entityBuffer1.getBounds().intersects(entityBuffer2.getBounds())){//Order of collision
                    physicalObjects.get(i).whenCollided(entityBuffer2);
                    physicalObjects.get(j).whenCollided(entityBuffer1);
                    break; //is i++ && j = i + 1 better ?
                }
            }
        }
    }
    public PlayGround getView() {
        return view;
    }

    public int getPlayerHealth(){
        return s1.getHealth();
    }
    public int getPlayerScore(){
        return s1.getScore();
    }
    private void loadPhotos() throws IOException {
        //This method will import all the photos needed for our game
        BufferedImage ph= ImageIO.read(new File("Resources/health.png"));
        allImages.put("health",(Image) ph);
    }

    public Image getPhoto(String name){
        return allImages.get(name);
    }

}
