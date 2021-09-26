import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TimerTask;

public class Model implements ActionListener, KeyListener {

    public static int HEIGHT=600,WIDTH=300;
    private  int delay=0;
    public Stick s1;
    public Ball b;
    private PlayGround view;
    private Timer timer;

    public ArrayList<Entity> physicalObjects = new ArrayList<>();
    private Enemy[] level1List = {  //new Enemy(0,0,600,600),
                                    //new Enemy(100,200,200,200, Color.GREEN),
                                    //new Enemy(200,100,200,200, Color.BLUE),
                                    //new Enemy(50,50,10,10,100,100,Color.RED),

                                    //new Enemy(400,400,500,500)
                                };
    //private Enemy[] level1List={};

    public Model(PlayGround p) {
        view = p;
        VerticalWall wallRight = new VerticalWall(WIDTH - 10, 0, 10, HEIGHT);
        VerticalWall wallLeft = new VerticalWall(0, 0, 10, HEIGHT);
        HorizontalWall wallUp = new HorizontalWall(10, 0, WIDTH-20, 10);
        s1 = new Stick(WIDTH / 2, HEIGHT - 20);
        b = new Ball(250, 580);

        addPhysicalObject(wallRight);
        addPhysicalObject(wallLeft);
        addPhysicalObject(wallUp);
        addPhysicalObject(s1);

        for(Enemy enemy : level1List)
            addPhysicalObject(enemy);



        for (Entity entity : physicalObjects) {
            view.addDrawable(entity);
        }
        view.addDrawable(b); //to remove

        //rendering in 120 frames per second
        timer = new Timer(9, this);
        timer.start();
        Timer timer1=new Timer(1000,view);
        timer1.start();




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        delay+= timer.getDelay();
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
        delay+=timer.getDelay();
        b.solveCollisions(physicalObjects);
        for(Entity enemy : physicalObjects){
            if(enemy instanceof Enemy)
                ((Enemy) enemy).move();
        }

        s1.move();
        b.move();
    }
}
