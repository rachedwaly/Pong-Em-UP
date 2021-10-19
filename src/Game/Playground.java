package Game;


import AltLib.ImageLoader;
import Entities.BackgroundObject;
import Entities.Entity;
import Frame.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Playground extends JPanel {
    public static int HEIGHT=600;
    public static int WIDTH=300;

    private StatusBar statusBar;
    private Model model;




    public Playground(Model model){
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

        g.drawString(Integer.toString(model.stick.innerTimer % 200) ,50,30 );
        ArrayList<Entity> drawingList=new ArrayList<>(model.getDrawables());
        //on doit impérativement utiliser ce type de boucle four sinon on peut pas mettre à jour
        // la liste  des drawables dynamiquement (sinon ça throw une exception)
        for (int i=0;i<drawingList.size();i++){
            Entity entity=drawingList.get(i);
            entity.drawEntity(g);
        }

        if (!model.isPlaying()){
            g.drawImage(ImageLoader.gameoverImage,20,120,model.getView());
        }

    }

    private void handleLevelBackground(Graphics g) {
        int lvl=model.getCurrentLvl();
        g.drawImage(ImageLoader.bgImage[lvl - 1], 0, 0, this);
        ArrayList<BackgroundObject> drawingList=new ArrayList<>(model.getBackgroundObjects());
        for (int i=0;i<drawingList.size();i++){
            BackgroundObject bgo=drawingList.get(i);
            bgo.drawEntity(g);
        }
    }


    public void update(){
        repaint();
        statusBar.repaint();
    }




}
