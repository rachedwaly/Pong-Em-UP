import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class PlayGround extends JPanel implements ActionListener, KeyListener {
    private StickPlayer1 s1;
    static int HEIGHT=600,WIDTH=300;
    private Timer timer;
    private Ball b;
    private int acceleration=0;
    private VerticalWall wallRight;
    private VerticalWall wallLeft;
    private HorizontalWall wallUp;
    private ArrayList<Entity> physicalObjects=new ArrayList<>();


    public PlayGround(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        s1=new StickPlayer1(WIDTH/2,HEIGHT-20);
        b=new Ball(250,580);
        wallRight=new VerticalWall(WIDTH-10,0,10,HEIGHT);
        wallLeft=new VerticalWall(0,0,10,HEIGHT);
        wallUp=new HorizontalWall(0,0,WIDTH,10);
        timer = new Timer(1, this);
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(WIDTH-10,0,10,HEIGHT);
        g.drawRect(0,0,10,HEIGHT);
        g.drawRect(0,0,WIDTH,10);
        drawObjects(g);
    }

    private void drawObjects(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(s1.getX(), s1.getY(), s1.getWidth(), s1.getHeight());
        g.fillOval(b.getX(),b.getY(),b.getWidth(),b.getWidth());


    }

    private void updateStick(){

        s1.move();
        repaint();


    }

    private void updateBall(){
        b.move();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        physicalObjects.add(wallUp);
        physicalObjects.add(wallLeft);
        physicalObjects.add(wallRight);
        physicalObjects.add(s1);
        b.solveCollisions(physicalObjects);
        physicalObjects.clear();
        updateBall();
        updateStick();



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
}
