package Game;

import Background.BackgroundObject;
import Entities.Entity;
import Frame.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class PlayGround extends JPanel {
    public static int HEIGHT=600;
    public static int WIDTH=300;

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
        handleLevelBackground(g);
        for (Entity entity : model.getDrawables()) {
            entity.superDrawEntity(g);
        }
    }
    private void handleLevelBackground(Graphics g) {
        int lvl=model.getCurrentLvl();
        g.drawImage(model.getPhoto("lvl"+lvl), 0, 0, this);
        for (BackgroundObject bgo:model.getBackgroundObjects()){
            bgo.drawEntity(g);
        }
    }


    public void update(){
        repaint();
        statusBar.repaint();
    }




}
