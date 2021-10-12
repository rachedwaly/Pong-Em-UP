import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class PlayGround extends JPanel {
    public static int HEIGHT=600;
    public static int WIDTH=300;
    private ArrayList<Entity> drawables=new ArrayList<>();
    private StatusBar statusBar;
    private Model model;




    public PlayGround(Model model) throws IOException {
        super(new BorderLayout()); //BorderLayout instead ?
        this.model=model;
        setPreferredSize(new Dimension(Model.WIDTH,Model.HEIGHT));
        statusBar=new StatusBar(model);
        add(statusBar,BorderLayout.SOUTH);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("(" + model.b.x + ", " + model.b.y + ")",50,30 );
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
        statusBar.repaint();
    }




}
