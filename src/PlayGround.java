import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.EventListener;


public class PlayGround extends JPanel implements KeyListener {

    public static int HEIGHT=600,WIDTH=300;
    private int acceleration=0;
    private Model model;

    public JButton gameToMenu; //Listener initialised in PongEmUp


    public PlayGround(){
        super(new FlowLayout()); //BorderLayout instead ?
        model = new Model(this);
        gameToMenu = new JButton("Menu");
        add(gameToMenu);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        //setFocusable(true);
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
        g.fillRect(model.s1.getX(), model.s1.getY(), model.s1.getWidth(), model.s1.getHeight());
        g.fillOval(model.b.getX(),model.b.getY(),model.b.getWidth(),model.b.getWidth());
        for(Entity entity : model.physicalObjects){
            g.setColor(entity.color);
            g.fillRect(entity.x,entity.y,entity.width,entity.height);
        }


    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        model.s1.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {

        model.s1.keyReleased(e);

    }

    public void update(){
        repaint();
    }

}
