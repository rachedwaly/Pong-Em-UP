package Frame;

import AltLib.ImageLoader;
import Entities.Bonus.Bonus;
import Game.Handler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Credits: rached
 * This class will handle the gray status Bar
 */
public class StatusBar extends JPanel {

    private Image healthPhoto;
    private Handler handler;

    public StatusBar(Handler handler){
        this.handler = handler;
        setBackground(new Color(211,211,211));
        setPreferredSize(new Dimension(Handler.WIDTH,50));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(ImageLoader.healthImage,10,10,29,30,this);
        g.setFont(new Font("Purisa", Font.BOLD, 13));
        g.drawString("X "+ handler.getPlayerSpawnLeft(),45,30);
        g.drawString("Score: "+ handler.getPlayerScore(),80,30);
        ArrayList<Bonus> bonuses= handler.getBonuses();
        int x=150;
        for (int i=0;i<bonuses.size();i++){
            if (bonuses.get(i).acquired) {
                bonuses.get(i).drawInStatusBar(g, x, 5);
                x += 40;
            }
        }
    }

    public void update(){
        repaint();
    }




}
