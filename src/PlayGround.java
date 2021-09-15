import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class PlayGround extends JPanel implements ActionListener, KeyListener {
    private StickPlayer1 s1;
    private StickPlayer2 s2;
    private Timer timer;
    private Ball b;
    private int acceleration=0;
    private Entity wallUp,wallDown;
    private ArrayList<Entity> physicalObjects=new ArrayList<>();


    public PlayGround(){
        setPreferredSize(new Dimension(500,300));
        s1=new StickPlayer1(20,150);
        s2=new StickPlayer2(470,150);
        b=new Ball(250,150);
        wallUp=new Entity(0,0,500,10,false);

        wallDown=new Entity(0,290,500,10,false);

        timer = new Timer(10, this);
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(0,0,500,10);
        g.drawRect(0,0,10,300);
        g.drawRect(0,290,500,10);
        g.drawRect(490,0,10,300);
        drawObjects(g);
    }

    private void drawObjects(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(s1.getX(), s1.getY(), s1.getWidth(), s1.getHeight());
        g.fillRect(s2.getX(), s2.getY(), s2.getWidth(), s2.getHeight());
        g.fillOval(b.getX(),b.getY(),b.getWidth(),b.getWidth());



    }

    private void updateSticks(){

        s1.move();
        s2.move();

    }

    private void updateBall(){
        b.move();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        physicalObjects.add(wallUp);
        physicalObjects.add(wallDown);
        physicalObjects.add(s1);
        physicalObjects.add(s2);
        b.solveCollisions(physicalObjects);
        physicalObjects.clear();
        updateSticks();
        updateBall();
        repaint();

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        s1.keyPressed(e);
        s2.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        s1.keyReleased(e);
        s2.keyReleased(e);
    }
}
