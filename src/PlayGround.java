import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PlayGround extends JPanel {
    public static int HEIGHT=600;
    public static int WIDTH=300;
    private ArrayList<Entity> drawables=new ArrayList<>();
    public JButton gameToMenu;
    public PlayGround(){
        super(new FlowLayout()); //BorderLayout instead ?
        gameToMenu = new JButton("Menu");
        add(gameToMenu);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.drawLine(0,200,300,200);
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
