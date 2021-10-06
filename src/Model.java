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

public class Model implements ActionListener, KeyListener {

    public static int HEIGHT=600,WIDTH=300;
    static final int DELAY = 8;


    private Stick s1;
    private Ball b;
    private HashMap<String,Image> allImages=new HashMap<>();
    private PlayGround view;
    private Timer timer;
    private int currentLvl=1;

    public ArrayList<Entity> physicalObjects = new ArrayList<>();
    private Enemy[] level1List = {
                                    new Enemy(Enemy.SENTRY,150,-50,150,200),
                                    new Enemy(Enemy.SENTRY,150,-50,200,200),
                                    new Enemy(Enemy.SENTRY,150,-50,50,100),
                                    new Enemy(Enemy.SENTRY,150,-50,100,150),
            new Enemy(Enemy.SENTRY,150,-50,250,350),
            new Enemy(Enemy.SENTRY,150,-50,20,300),
                                    //new Enemy(400,400,500,500)
                                };


    public Model() throws IOException {
        loadPhotos();
        view = new PlayGround(this);
        VerticalWall wallRight = new VerticalWall(WIDTH - 10, 0, 10, HEIGHT);
        VerticalWall wallLeft = new VerticalWall(0, 0, 10, HEIGHT);
        HorizontalWall wallUp = new HorizontalWall(10, 0, WIDTH-20, 10);
        s1 = new Stick(WIDTH / 2, HEIGHT - 20);
        b = new Ball(250, 580);

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

        b.solveCollisions(physicalObjects);

        for(Entity e : physicalObjects){
            e.update();
        }

        b.update(); //b updated separately else it collides with itself
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


    public void stopTimer(){
        timer.stop();
    }
    public void startTimer(){
        timer.start();
    }

    private void loadPhotos() throws IOException {
        //This method will import all the photos needed for our game
        BufferedImage ph= ImageIO.read(new File("Resources/health.png"));
        allImages.put("health",(Image) ph);
        BufferedImage ph1= ImageIO.read(new File("Resources/background lvl1.jpg"));
        allImages.put("lvl1",(Image)ph1);
        BufferedImage ph2= ImageIO.read(new File("Resources/background lvl2.jpg"));
        allImages.put("lvl2",(Image)ph2);
    }

    public Image getPhoto(String name){
        return allImages.get(name);
    }

    public int getCurrentLvl() {
        return currentLvl;
    }
}
