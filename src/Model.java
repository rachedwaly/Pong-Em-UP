import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Model implements ActionListener, KeyListener {

    public static int HEIGHT=600,WIDTH=300;

    public Stick s1;
    public Ball b;
    private PlayGround view;
    private Timer timer;
    private ArrayList<Entity> physicalObjects=new ArrayList<>();


    public Model(PlayGround p) {
        view = p;
        VerticalWall wallRight = new VerticalWall(WIDTH - 10, 0, 10, HEIGHT);
        VerticalWall wallLeft = new VerticalWall(0, 0, 10, HEIGHT);
        HorizontalWall wallUp = new HorizontalWall(0, 0, WIDTH, 10);
        s1 = new Stick(WIDTH / 2, HEIGHT - 20);
        b = new Ball(250, 580);
        addPhysicalObject(wallRight);
        addPhysicalObject(wallLeft);
        addPhysicalObject(wallUp);
        addPhysicalObject(s1);


        for (Entity entity : physicalObjects) {
            view.addDrawable(entity);
        }
        view.addDrawable(b); //to remove
        timer = new Timer(1, this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
        view.update();
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
        s1.move();
        b.solveCollisions(physicalObjects);
        b.move();
    }

    public void addPhysicalObject(Entity e){
        physicalObjects.add(e);
    }

}
