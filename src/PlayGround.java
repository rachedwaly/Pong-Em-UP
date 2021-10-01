import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PlayGround extends JPanel {
    public static int HEIGHT=600;
    public static int WIDTH=300;
    private ArrayList<Entity> drawables=new ArrayList<>();

    public PlayGround(){
        super(new BorderLayout()); //BorderLayout instead ?
        setPreferredSize(new Dimension(Model.WIDTH,Model.HEIGHT));
        JPanel statusBar=new JPanel();

        statusBar.setBackground(Color.GRAY);
        statusBar.setPreferredSize(new Dimension(Model.WIDTH,50));
        add(statusBar,BorderLayout.SOUTH);
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
