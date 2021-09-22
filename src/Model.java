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
    public ArrayList<Entity> physicalObjects = new ArrayList<>();
    private Enemy[] level1List = {new Enemy(), new Enemy(), new Enemy()};

    public Model(PlayGround p){
        VerticalWall wallRight = new VerticalWall(WIDTH-10,0,10,HEIGHT);
        VerticalWall wallLeft = new VerticalWall(0,0,10,HEIGHT);
        HorizontalWall wallUp = new HorizontalWall(0,0,WIDTH,10);

        invariableObjects.add(wallRight);
        invariableObjects.add(wallLeft);
        invariableObjects.add(wallUp);

        physicalObjects.add(level1List[0]);
        listener = p;
        s1 = new StickPlayer1(WIDTH/2,HEIGHT-20);
        invariableObjects.add(s1);
        b=new Ball(250,580);
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        b.solveCollisions(invariableObjects);
        b.solveCollisions(physicalObjects);
        s1.move();
        b.move();
        listener.update();
    }

}
