import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Model implements ActionListener {

    public static int HEIGHT=600,WIDTH=300;

    public StickPlayer1 s1;
    public Ball b;
    private PlayGround listener;
    private Timer timer;
    private ArrayList<Entity> invariableObjects = new ArrayList<>();
    private ArrayList<Entity> physicalObjects=new ArrayList<>();

    public Model(PlayGround p){
        VerticalWall wallRight = new VerticalWall(WIDTH-10,0,10,HEIGHT);
        VerticalWall wallLeft = new VerticalWall(0,0,10,HEIGHT);
        HorizontalWall wallUp = new HorizontalWall(0,0,WIDTH,10);
        invariableObjects.add(wallRight);
        invariableObjects.add(wallLeft);
        invariableObjects.add(wallUp);

        listener = p;
        s1 = new StickPlayer1(WIDTH/2,HEIGHT-20);
        b=new Ball(250,580);
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*physicalObjects.add(wallUp);
        physicalObjects.add(wallLeft);
        physicalObjects.add(wallRight);*/
        physicalObjects.add(s1);
        b.solveCollisions(invariableObjects);
        b.solveCollisions(physicalObjects);
        physicalObjects.clear();
        s1.move();
        b.move();
        listener.update();
    }

}
