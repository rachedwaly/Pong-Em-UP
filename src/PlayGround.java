import Sprites.Sprite;

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
    private ArrayList<Sprite> sprites=new ArrayList<Sprite>();




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

        handleLevelBackground(g);


        for (Entity entity : drawables) {
            entity.drawEntity(g);
        }
    }

    private void handleLevelBackground(Graphics g) {
        int lvl=model.getCurrentLvl();
        g.drawImage(model.getPhoto("lvl"+lvl), 0, 0, this);
        switch(lvl){
            case 1:{
                //generate sprites for lvl1

                break;
            }
            case 2:{
                //generate sprites for lvl2
                break;
            }
            case 3:{
                //generate sprites for lvl3
                break;
            }
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
