import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class PlayGround extends JPanel  {

    static int HEIGHT=600,WIDTH=300;
    private ArrayList<Entity> drawables=new ArrayList<>();
    public JButton gameToMenu;

    public PlayGround(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        gameToMenu=new JButton("Menu");
        add(gameToMenu);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Entity entity : drawables) {
            entity.drawEntity(g);
        }
    }

    public void addDrawable(Entity e){
        drawables.add(e);
    }
    public void removeDrawable(Entity e){
        drawables.remove(e);
    }
    public void update(){
        repaint();
    }

}
