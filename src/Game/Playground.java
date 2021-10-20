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
    private Handler handler;




    public Playground(Handler handler){
        super(new BorderLayout()); //BorderLayout instead ?
        this.handler = handler;
        setPreferredSize(new Dimension(Handler.WIDTH, Handler.HEIGHT));
        statusBar=new StatusBar(handler);
        add(statusBar,BorderLayout.SOUTH);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        handleLevelBackground(g);

        //g.drawString(Integer.toString(handler.stick.innerTimer % 200) ,50,30 );
        ArrayList<Entity> drawingList=new ArrayList<>(handler.getDrawables());
        //on doit impérativement utiliser ce type de boucle four sinon on peut pas mettre à jour
        // la liste  des drawables dynamiquement (sinon ça throw une exception)
        for (int i=0;i<drawingList.size();i++){
            Entity entity=drawingList.get(i);
            entity.drawEntity(g);
        }

        if (!handler.isPlaying()){
            g.drawImage(ImageLoader.gameoverImage,20,120, handler.getView());
        }

    }

    private void handleLevelBackground(Graphics g) {
        int lvl= handler.getCurrentLvl();
        g.drawImage(ImageLoader.bgImage[lvl - 1], 0, 0, this);
        ArrayList<BackgroundObject> drawingList=new ArrayList<>(handler.getBackgroundObjects());
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
