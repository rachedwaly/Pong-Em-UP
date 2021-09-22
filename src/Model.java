import javax.swing.*;
import java.awt.*;
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
    private Enemy[] level1List = {  //new Enemy(0,0,600,600),
                                    new Enemy(100,200,200,200, Color.GREEN),
                                    new Enemy(200,100,200,200, Color.BLUE),
                                    new Enemy(100,100,10,10,100,100,Color.RED)
                                    //new Enemy(400,400,500,500)
                                };

    public Model(PlayGround p){
        VerticalWall wallRight = new VerticalWall(WIDTH-10,0,10,HEIGHT);
        VerticalWall wallLeft = new VerticalWall(0,0,10,HEIGHT);
        HorizontalWall wallUp = new HorizontalWall(0,0,WIDTH,10);

        invariableObjects.add(wallRight);
        invariableObjects.add(wallLeft);
        invariableObjects.add(wallUp);

        for(Enemy enemy : level1List)
            physicalObjects.add(enemy);

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
        for(Entity enemy : physicalObjects){
            if(enemy instanceof Enemy)
                ((Enemy) enemy).move();
        }
        //level1List[0].move();
        s1.move();
        b.move();
        listener.update();
    }

}
